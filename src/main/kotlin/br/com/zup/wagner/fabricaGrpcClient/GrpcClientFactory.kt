package br.com.zup.wagner.fabricaGrpcClient

import br.com.zup.wagner.KeyManagerCarregaChavePixServiceGrpc
import br.com.zup.wagner.KeyManagerCarregaTodasChavePixServiceGrpc
import br.com.zup.wagner.KeyManagerRegistraChavePixServiceGrpc
import br.com.zup.wagner.KeyManagerRemoveChavePixServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

// fabrica de servidor grpc--------------------------------------------------------------
// anotação é para avisar o micronaut, que esta classe é uma fabrica
@Factory
class GrpcClientFactory {

    // 1º bean para registrar chave pix

    @Singleton
    fun registraChaveStub(@GrpcChannel("keyManagerRestApi") channel: ManagedChannel) : KeyManagerRegistraChavePixServiceGrpc.KeyManagerRegistraChavePixServiceBlockingStub? {
        return KeyManagerRegistraChavePixServiceGrpc.newBlockingStub(channel)
    }

    // 2º bean para remover chave pix

    @Singleton
    fun removeChavesStub(@GrpcChannel("keyManagerRestApi") channel: ManagedChannel) : KeyManagerRemoveChavePixServiceGrpc.KeyManagerRemoveChavePixServiceBlockingStub? {
        return KeyManagerRemoveChavePixServiceGrpc.newBlockingStub(channel)
    }

    // 3 bean para detalhamento de uma chave pix, consulta

    @Singleton
    fun consultaChaveStub(@GrpcChannel("keyManagerRestApi") channel: ManagedChannel): KeyManagerCarregaChavePixServiceGrpc.KeyManagerCarregaChavePixServiceBlockingStub? {
        return KeyManagerCarregaChavePixServiceGrpc.newBlockingStub(channel)
    }

    // 4 bean listar todas as chaves pix

    @Singleton
    fun listarTodas(@GrpcChannel("keyManagerRestApi") channel: ManagedChannel): KeyManagerCarregaTodasChavePixServiceGrpc.KeyManagerCarregaTodasChavePixServiceBlockingStub {
        return KeyManagerCarregaTodasChavePixServiceGrpc.newBlockingStub(channel)
    }

}