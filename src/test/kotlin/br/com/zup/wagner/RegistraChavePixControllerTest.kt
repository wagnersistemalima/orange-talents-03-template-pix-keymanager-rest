package br.com.zup.wagner

import br.com.zup.wagner.novaChavePix.request.NovaChavePixRequest
import br.com.zup.wagner.novaChavePix.request.TipoDeChaveEnum
import br.com.zup.wagner.novaChavePix.request.TipoDeContaEnum
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.util.*
import javax.inject.Inject


@MicronautTest
class RegistraChavePixControllerTest {

    @field:Inject
    lateinit var grpcClient: KeyManagerRegistraChavePixServiceGrpc.KeyManagerRegistraChavePixServiceBlockingStub

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @BeforeEach
    fun setUp() {
        Mockito.reset(grpcClient)
    }

    @Test
    @DisplayName("deve cadastrar chave pix , quando a request estiver validada")
    fun deveRegistrarChavePix() {

        // cenario

        val clienteId = UUID.randomUUID().toString()
        val pixId = UUID.randomUUID().toString()

        // ação

        val response = RegistraChavePixResponse.newBuilder()
            .setClientId(clienteId)
            .setPixId(pixId)
            .build()

        given(grpcClient.registra(Mockito.any())).willReturn(response)


        val novaChavePix = NovaChavePixRequest(
            tipoDeChave = TipoDeChaveEnum.CPF,
            chave = "04394450438",
            tipoDeConta = TipoDeContaEnum.CONTA_CORRENTE
        )

        val request = HttpRequest.POST("/api/v1/clientes/${clienteId}/pix", novaChavePix)

        val resposta = client.toBlocking().exchange<NovaChavePixRequest, Any>(request)

        // assertivas

       assertEquals(HttpStatus.CREATED.code, resposta.status.code)

    }
}

