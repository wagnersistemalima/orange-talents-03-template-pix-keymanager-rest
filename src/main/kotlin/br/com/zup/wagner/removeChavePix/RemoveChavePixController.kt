package br.com.zup.wagner.removeChavePix

import br.com.zup.wagner.DeleteChavePixRequest
import br.com.zup.wagner.KeyManagerRemoveChavePixServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.QueryValue
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/api/v1/clientes/{clienteId}")     // controller responde nesta rota
class RemoveChavePixController(
    private val grpcRemoveClient: KeyManagerRemoveChavePixServiceGrpc.KeyManagerRemoveChavePixServiceBlockingStub
) {

    private  val logger = LoggerFactory.getLogger(RemoveChavePixController::class.java)

    // end point

    @Delete("/pix/{identificadorItau}")
    fun removeChave(@QueryValue clienteId: UUID, @QueryValue identificadorItau: UUID): HttpResponse<Any> {
        logger.info("Iniciando a remoção de uma chave pix")

        grpcRemoveClient.delete(DeleteChavePixRequest.newBuilder()
            .setPixId(clienteId.toString())
            .setIdentificadorItau(identificadorItau.toString())
            .build())

        logger.info("Remoção de chave pix concluida com sucesso")

        return HttpResponse.ok()

    }
}