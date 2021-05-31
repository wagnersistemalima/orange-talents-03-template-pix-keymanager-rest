package br.com.zup.wagner.consultaChavePix.controller

import br.com.zup.wagner.CarregaChavePixRequest
import br.com.zup.wagner.CarregaChavePixResponse
import br.com.zup.wagner.KeyManagerCarregaChavePixServiceGrpc
import br.com.zup.wagner.consultaChavePix.response.DetalhesChavePixResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/api/v1/clientes/{clienteId}")
class ConsultaDetalhesChavePixController(
    private val grpcConsultaClient: KeyManagerCarregaChavePixServiceGrpc.KeyManagerCarregaChavePixServiceBlockingStub
) {

    private val logger = LoggerFactory.getLogger(ConsultaDetalhesChavePixController::class.java)

    // end point

    @Get("/pix/{pixId}")
    fun consulta(@QueryValue clienteId: UUID, @QueryValue pixId: UUID): HttpResponse<Any> {
        logger.info("Iniciando a consulta...")

        val chaveResponse = grpcConsultaClient.carrega(CarregaChavePixRequest.newBuilder()
            .setPixId(CarregaChavePixRequest.FiltroPorPixId.newBuilder()
                .setClientId(clienteId.toString())
                .setPixId(pixId.toString())
                .build())


            .build())


        return HttpResponse.ok(DetalhesChavePixResponse(chaveResponse))
    }
}