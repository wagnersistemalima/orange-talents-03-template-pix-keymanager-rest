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
class ConsultaDetalhesChavePixControllerTest {

    @field:Inject
    lateinit var grpcConsultaClient: KeyManagerCarregaChavePixServiceGrpc.KeyManagerCarregaChavePixServiceBlockingStub

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    val identificadorItau = UUID.randomUUID()
    val tipoDeConta = "CONTA_CORRENTE"
    val valorChave = "02467781054"                     //cpf
    val instituicao = "ITAÚ UNIBANCO S.A."
    val ispb = "60701190"
    val agencia = "0001"
    val numeroConta = "291900"
    val id = "c56dfef4-7901-44fb-84e2-a2cefb157890"
    val nomeTitular = "Rafael M C Ponte"
    val cpf = "02467781054"     // cpf do Rafael Pontes
    val tipoDeChave = "CPF"
    val criadoEm = "2021-05-27T14:42:28.138700"

    private fun carregaChavePixResponse(clientId: String, pixId: String) =
        CarregaChavePixResponse.newBuilder()
            .setIdentificadorItau(identificadorItau.toString())
            .setTipoDeChave(tipoDeChave)
            .setChave(valorChave)
            .setTipoDeConta(tipoDeConta)
            .setInstituicao(instituicao)
            .setIspb(ispb)
            .setAgencia(agencia)
            .setNumeroConta(numeroConta)
            .setTitular(nomeTitular)
            .setCpf(cpf)
            .setIdInterno(id)
            .setCriadoEm(criadoEm)

            .build()


    // rodar antes de cada teste

    @BeforeEach
    internal fun setUp() {
        Mockito.reset(grpcConsultaClient)
    }

    // 1 cenario de teste

    @Test
    @DisplayName("deve retornar detalhes da chave pix quando  a consulta do cliente for solicitado")
    fun deveRetornarDetalhesChavePixQuandoConsultaDoClienteSolicitado() {

        // cenario

        val clientId: UUID = UUID.randomUUID()
        val pixId: UUID = UUID.randomUUID()

        given(grpcConsultaClient.carrega(Mockito.any())).willReturn(carregaChavePixResponse(clientId.toString(), pixId.toString()))

        val request = HttpRequest.GET<Any>("/api/v1/clientes/${clientId}/pix/${pixId}")

        val response = client.toBlocking().exchange(request, Any::class.java)

        // ação

        // assertivas

        assertEquals(HttpStatus.OK.code, response.status.code)
    }
}