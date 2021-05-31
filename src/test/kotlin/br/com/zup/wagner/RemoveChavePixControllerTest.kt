package br.com.zup.wagner

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
class RemoveChavePixControllerTest {

    @field:Inject
    lateinit var grpcRemoveClient: KeyManagerRemoveChavePixServiceGrpc.KeyManagerRemoveChavePixServiceBlockingStub

    @field:Client("/")
    @field:Inject
    lateinit var client: HttpClient

    // rodar antes de cada teste

    @BeforeEach
    internal fun setUp() {
        Mockito.reset(grpcRemoveClient)
    }

    // 1º cenario de teste

    @Test
    @DisplayName("deve remover chave pix")
    fun deveRemoverChavePix() {

        // cenario

        val clientId: UUID = UUID.randomUUID()
        val identificadorItau: UUID = UUID.randomUUID()

        //ação

        val responseGrpc = DeleteChavePixResponse.newBuilder()
            .setPixId(clientId.toString())
            .setIdentificadorItau(identificadorItau.toString())
            .build()

        given(grpcRemoveClient.delete(Mockito.any())).willReturn(responseGrpc)

        val request = HttpRequest.DELETE<Any>("/api/v1/clientes/${clientId}/pix/${identificadorItau}")
        val response = client.toBlocking().exchange(request, Any::class.java)


        //assertivas

        assertEquals(HttpStatus.OK, response.status)


    }


}