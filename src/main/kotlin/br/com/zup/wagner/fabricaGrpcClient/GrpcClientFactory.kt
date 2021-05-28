package br.com.zup.wagner.fabricaGrpcClient

import br.com.zup.wagner.KeyManagerRegistraChavePixServiceGrpc
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

}