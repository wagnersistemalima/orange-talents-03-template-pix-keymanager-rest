package br.com.zup.wagner.novaChavePix.validador


import br.com.zup.wagner.novaChavePix.request.NovaChavePixRequest
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

// anotação

@MustBeDocumented
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidPixKeyValidator::class])
annotation class ValidPixKey(
    val message: String = "chave pix invalida (\${validatedValue.tipoDeChave})",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
)

// validação

@Singleton
class ValidPixKeyValidator : ConstraintValidator<ValidPixKey, NovaChavePixRequest> {
    override fun isValid(value: NovaChavePixRequest?, context: ConstraintValidatorContext?): Boolean {
        if (value?.tipoDeChave == null) {
            return false
        }

        return value.tipoDeChave.valida(value.chave)
    }

}
