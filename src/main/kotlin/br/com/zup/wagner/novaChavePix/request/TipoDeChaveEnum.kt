package br.com.zup.wagner.novaChavePix.request

import br.com.zup.wagner.TipoDeChave
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator

enum class TipoDeChaveEnum(
    val atributoGrpc: TipoDeChave
) {

    CPF(TipoDeChave.CPF) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            if (!chave.matches("^[0-9]{11}\$".toRegex())) {
                return false
            }

            return CPFValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },
    CELULAR(TipoDeChave.CELULAR) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }
            return chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())
        }
    },
    EMAIL(TipoDeChave.EMAIL) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            return chave.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$".toRegex())
        }
    },
    ALEATORIA(TipoDeChave.ALEATORIA) {
        override fun valida(chave: String?) = chave.isNullOrBlank() // não deve se preenchida
    };

    abstract fun valida(chave: String?): Boolean // metodo abstrato para fazer validação


}