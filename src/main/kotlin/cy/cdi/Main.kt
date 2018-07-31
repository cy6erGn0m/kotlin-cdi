package cy.cdi

import org.jboss.weld.environment.se.*
import javax.inject.*
import javax.validation.*
import javax.validation.constraints.*


@Singleton
class Main {
    @Size(min = 0, max = 2, message = "{yo shouldn't be longer than 2 characters}")
    var yo: String = ""

    @Inject
    lateinit var testBean: TestBean

    fun main() {
        println("Hello!")
        println("testBean = $testBean")
        testBean.ok()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Weld().enableDiscovery().enableDevMode().initialize().use { container ->
                val m = container.select(Main::class.java).get()
                m.main()
                m.yo = "123"
                println(m.yo)
                val validator = Validation.buildDefaultValidatorFactory().validator

                validator.validate(m).forEach { violation ->
                    println(violation.message)
                }
            }
        }
    }
}