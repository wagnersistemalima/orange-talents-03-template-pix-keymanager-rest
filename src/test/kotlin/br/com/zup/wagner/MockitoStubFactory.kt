package br.com.zup.wagner

//import br.com.zup.wagner.fabricaGrpcClient.GrpcClientFactory
import br.com.zup.wagner.fabricaGrpcClient.GrpcClientFactory
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import org.mockito.Mockito
import javax.inject.Singleton

@Factory
@Replaces(factory = GrpcClientFactory::class)
class MockitoStubFactory {

    @Singleton
    fun stubMock() = Mockito.mock(KeyManagerRegistraChavePixServiceGrpc.KeyManagerRegistraChavePixServiceBlockingStub::class.java)


}