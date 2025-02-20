package br.com.zup.wagner.exceptions

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.slf4j.LoggerFactory
import javax.inject.Singleton

// clase responsavel por tratar erros vindo do microserviço KeyManager, e responder
// ao cliente rest da nossa api

@Singleton
class ExceptionHandllerInterceptor: ExceptionHandler<StatusRuntimeException, HttpResponse<Any>> {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    override fun handle(request: HttpRequest<*>, exception: StatusRuntimeException): HttpResponse<Any> {
        val statusCode = exception.status.code
        val statusDescription = exception.status.description ?: ""

        val (httpStatus, message) = when(statusCode){
            Status.NOT_FOUND.code -> Pair(HttpStatus.NOT_FOUND, statusDescription)
            Status.FAILED_PRECONDITION.code -> Pair(HttpStatus.BAD_REQUEST, statusDescription)
            Status.ALREADY_EXISTS.code -> Pair(HttpStatus.UNPROCESSABLE_ENTITY, statusDescription)
            else -> {
                LOGGER.error("Error inesperado ${exception.javaClass.name} ao processar requisição", exception.message)
                Pair(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível completar o cadastro")
            }
        }

        return HttpResponse
            .status<JsonError>(httpStatus)
            .body(JsonError(message))
    }
}