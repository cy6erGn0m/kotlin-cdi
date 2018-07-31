package cy.cdi

import javax.decorator.*
import javax.inject.*

interface Component {
    fun f1()
    fun f2()
}


open class ComponentImpl : Component {
    override fun f1() {
        println("f1")
    }

    override fun f2() {
        println("f2")
    }
}

@Decorator
open class ComponentDecorator : Component {
    @Inject
    @Delegate
    lateinit var delegate: Component

    override fun f1() {
        println("decorated f1")
    }

    override fun f2() {
        delegate.f2()
    }
}
