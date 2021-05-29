package br.com.zup.wagner.novaChavePix.controller

import br.com.zup.wagner.KeyManagerRegistraChavePixServiceGrpc
import br.com.zup.wagner.novaChavePix.request.NovaChavePixRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/api/v1/clientes/{clienteId}")
class RegistraChavePixController(
    @Inject val grpcClient: KeyManagerRegistraChavePixServiceGrpc.KeyManagerRegistraChavePixServiceBlockingStub
) {

    private val logger = LoggerFactory.getLogger(RegistraChavePixController::class.java)

    // end point rest

    @Post("/pix")
    fun registra(@QueryValue clienteId: UUID, @Body @Valid request: NovaChavePixRequest): HttpResponse<Any> {
        logger.info("Iniciando api rest.....")

        val grpcResponse = grpcClient.registra(request.toModel(clienteId))

        return HttpResponse.created(location(clienteId, grpcResponse.pixId))
    }

    private fun location(clientId: UUID, pixId: String) = HttpResponse.uri("/api/v1/clients/${clientId}/pix/${pixId}")
}