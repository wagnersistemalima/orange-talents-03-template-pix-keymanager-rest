package br.com.zup.wagner.listarTodasChaves.response

import br.com.zup.wagner.ListarTodasPixResponse
import io.micronaut.core.annotation.Introspected

// obs não usei o data class, porque tive problemas com jackson para desserializar o objeto
// lista do tipo Grpc. Talvez por conta do -repeated- da response no proto

@Introspected
class ChavePixResponse(
    lista: ListarTodasPixResponse.ChavePix
) {

    val pixId = lista.pixId
    val clientId = lista.clientId
    val tipoDeChave = lista.tipoDeChave
    val tipoDeConta = lista.tipoDaConta
    val criadoEm = lista.criadoEm
}