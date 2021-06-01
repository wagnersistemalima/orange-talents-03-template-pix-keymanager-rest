package br.com.zup.wagner.listarTodasChaves.controller

import br.com.zup.wagner.KeyManagerCarregaTodasChavePixServiceGrpc
import br.com.zup.wagner.ListarTodasPixRequest
import br.com.zup.wagner.listarTodasChaves.response.ChavePixResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Inject


@Controller("/api/v1/clientes/{clienteId}")
class ListarTodasChavesPixController(
    @Inject private val grpcListarCliente: KeyManagerCarregaTodasChavePixServiceGrpc.KeyManagerCarregaTodasChavePixServiceBlockingStub
) {

    private val logger = LoggerFactory.getLogger(ListarTodasChavesPixController::class.java)

    // 1 end point listar todas

    @Get("/contas")
    fun listarTodas(@QueryValue clienteId: UUID): HttpResponse<Any> {
        logger.info("Iniciando pesquisa de listar todas chave pix de um cliente")

        val responseGrpc = grpcListarCliente.listarTodas(ListarTodasPixRequest.newBuilder()
            .setClientId(clienteId.toString())
            .build())

        val chaves = responseGrpc.chavesList.map { it -> ChavePixResponse(it) }

        return HttpResponse.ok(chaves)

    }
}