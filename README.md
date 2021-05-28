# orange-talents-03-template-pix-keymanager-rest

## Micronaut 2.5.4 Documentation

- [User Guide](https://docs.micronaut.io/2.5.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.5.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.5.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


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
