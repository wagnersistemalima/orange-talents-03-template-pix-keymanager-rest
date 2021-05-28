package br.com.zup.wagner.novaChavePix.request

import br.com.zup.wagner.TipoDeConta

// classe enum, convertendo para cada entrada rest converter para entrada grpc

enum class TipoDeContaEnum(
    val atributoGrpc: TipoDeConta
) {

    CONTA_CORRENTE(TipoDeConta.CONTA_CORRENTE),
    CONTA_POUPANCA(TipoDeConta.CONTA_POUPANCA);


}