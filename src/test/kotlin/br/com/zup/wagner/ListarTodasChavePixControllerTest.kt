package br.com.zup.wagner

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.util.*
import javax.inject.Inject


@MicronautTest
class ListarTodasChavePixControllerTest {

    @field:Inject
    lateinit var grpcListarClient: KeyManagerCarregaTodasChavePixServiceGrpc.KeyManagerCarregaTodasChavePixServiceBlockingStub

    @field:Client("/")
    @field:Inject
    lateinit var client: HttpClient

    val identificadorItau = UUID.randomUUID()
    val tipoDeConta = "CONTA_CORRENTE"

    val tipoDeChave = "CPF"
    val tipoDeChaveEmail = "rafael@zup.com"
    val criadoEm = "2021-05-27T14:42:28.138700"
    val idInternoCpf: UUID = UUID.randomUUID()
    val idInternoEmail: UUID = UUID.randomUUID()

    fun listaChavePixResponse(clientId: String): ListarTodasPixResponse {
        val chaveCpf = ListarTodasPixResponse.ChavePix.newBuilder()
            .setPixId(idInternoCpf.toString())
            .setClientId(identificadorItau.toString())
            .setTipoDeChave(tipoDeChave)
            .setTipoDaConta(tipoDeConta)
            .setCriadoEm(criadoEm)

            .build()

        val chaveEmail = ListarTodasPixResponse.ChavePix.newBuilder()
            .setPixId(idInternoEmail.toString())
            .setClientId(identificadorItau.toString())
            .setTipoDeChave(tipoDeChaveEmail)
            .setTipoDaConta(tipoDeConta)
            .setCriadoEm(criadoEm)

            .build()


        return ListarTodasPixResponse.newBuilder()
            .setClientId(clientId)
            .addAllChaves(listOf(chaveCpf, chaveEmail))
            .build()

    }

    // rodar antes de cada teste

    @BeforeEach
    internal fun setUp() {
        Mockito.reset(grpcListarClient)
    }

    // 1 cenario de teste

    @Test
    @DisplayName("deve retornar lista de chave pix do cliente solicitado")
    fun deveRetornarListaDeChavePix() {

        // cenario

        val clientId: UUID = UUID.randomUUID()

        val responseGrpc = listaChavePixResponse(clientId.toString())

        given(grpcListarClient.listarTodas(Mockito.any())).willReturn(responseGrpc)

        val request = HttpRequest.GET<Any>("/api/v1/clientes/${clientId}/contas")

        val response = client.toBlocking().exchange(request, List::class.java)

        // ação

        // assertivas

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(response.body().size, 2)
    }
}