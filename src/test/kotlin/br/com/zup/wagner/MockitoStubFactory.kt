package br.com.zup.wagner

//import br.com.zup.wagner.fabricaGrpcClient.GrpcClientFactory
import br.com.zup.wagner.fabricaGrpcClient.GrpcClientFactory
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import org.mockito.Mockito
import javax.inject.Singleton

// fabrica de mock--------------end point do microserviço grpc

@Factory
@Replaces(factory = GrpcClientFactory::class)
class MockitoStubFactory {

    @Singleton
    fun registraMock() = Mockito.mock(KeyManagerRegistraChavePixServiceGrpc.KeyManagerRegistraChavePixServiceBlockingStub::class.java)

    @Singleton
    fun deleteMock() = Mockito.mock(KeyManagerRemoveChavePixServiceGrpc.KeyManagerRemoveChavePixServiceBlockingStub::class.java)
}