# orange-talents-03-template-pix-keymanager-rest

# GRPC -> Chamada de Procedimento Remoto
* Framework da google

# Caracteristicas:
* Simple & idiomatic
* Performant & scalable
* Interoperable & extensible
* Funciona em cima do http2 -> https
* Util para microsserviços
* Utiliza protobuf, formato binario para trafegar na rede

### Protobuf
* Uma alternativa para a serialização em JSON e XML
* Ele faz de forma binaria
* Desta forma, os dados ficam bem menores e compactos, e podem ser trafegados muito mais rapidamente, e com menor
custo na rede, persistidos em discos

# consumindo-endpoint-rest
* KeyManager-REST: microsserviço responsável por expor serviço KeyManager-gRPC através de uma API REST de tal forma que ela possa ser consumida pelo time de frontend de forma eficiente e segura;

## Ferramentas

* Application Type: gRPC Application
* Micronaut Version: 2.5.4
* Java Version: 11
* Language: Kotlin
* Build: Gradlle Kotlin
* Test Framework: Junit
