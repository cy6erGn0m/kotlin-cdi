package cy.cdi

import javax.interceptor.*
import kotlin.system.*

@InterceptorBinding
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Logged

@Logged
@Interceptor
class LoggerInterceptor {
    @AroundInvoke
    fun invoke(ctx: InvocationContext): Any? {
        var result: Any? = null
        val time = measureTimeMillis {
            result = ctx.proceed()
        }

        println("invocation ${ctx.method.name} took $time ms")
        return result
    }
}