package br.com.zup.wagner.consultaChavePix.response

import br.com.zup.wagner.CarregaChavePixResponse
import io.micronaut.core.annotation.Introspected

@Introspected
data class DetalhesChavePixResponse(
    var id: String?,                     //opcional
    var identificadorItau: String?,       //opcional
    var tipoChave: String?,         //ok
    var chave: String?,          //ok
    var tipoDeConta: String?,     //ok
    var instituicao: String?,     // ok
    var ispb: String?,
    var agencia: String?,           //ok
    var numeroConta: String?,      //ok
    var titular: String?,         // ok
    var cpf: String?,             //ok
    var criadoEm: String?         //ok
) {

    // construtor personalizado recebendo um objeto do grpc client

    constructor(chaveResponse: CarregaChavePixResponse) : this(
        id = chaveResponse.idInterno,
        identificadorItau = chaveResponse.identificadorItau,
        tipoChave = chaveResponse.tipoDeChave,
        chave = chaveResponse.chave,
        tipoDeConta = chaveResponse.tipoDeConta,
        instituicao = chaveResponse.instituicao,
        ispb = chaveResponse.ispb,
        agencia = chaveResponse.agencia,
        numeroConta = chaveResponse.numeroConta,
        titular = chaveResponse.titular,
        cpf = chaveResponse.cpf,
        criadoEm = chaveResponse.criadoEm
    )

}





