package br.com.zup.wagner.novaChavePix.request

import br.com.zup.wagner.RegistraChavePixRequest
import br.com.zup.wagner.TipoDeChave
import br.com.zup.wagner.TipoDeConta
import br.com.zup.wagner.novaChavePix.validador.ValidPixKey
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ValidPixKey
@Introspected
data class NovaChavePixRequest(

    @field:NotNull
    val tipoDeChave: TipoDeChaveEnum?,

    @field:Size(max = 77)
    val chave: String?,

    @field:NotNull
    val tipoDeConta: TipoDeContaEnum?
) {

    // metodo para converter request em um modelo grpc

    fun toModel(clienteId: UUID): RegistraChavePixRequest {
        return RegistraChavePixRequest.newBuilder()
            .setClientId(clienteId.toString())
            .setTipoDeChave(tipoDeChave?.atributoGrpc ?: TipoDeChave.DEFAULT_TIPO_CHAVE)
            .setValorChave(chave ?: "")
            .setTipoDeConta(tipoDeConta?.atributoGrpc ?: TipoDeConta.DEFAULT_TIPO_DE_CONTA)
            .build()
    }
}
