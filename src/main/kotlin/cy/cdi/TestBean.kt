package cy.cdi

import javax.annotation.*
import javax.enterprise.event.*
import javax.enterprise.inject.spi.*
import javax.inject.*
import javax.persistence.*

@Logged
open class TestBean {
    @Inject
    open lateinit var em: EntityManager

    @Inject
    open lateinit var bm: BeanManager

    @Inject
    @SpecializedAnalyzer
    open lateinit var a: Analyzer

    @Inject
    open lateinit var component: Component

    @Inject
    open lateinit var evt: Event<MyEvent>

    @PostConstruct
    open fun before() {
        println("before...")
    }

    open fun ok() {
        println("found = " + bm.getBeans(TestBean::class.java));

        em.transaction.begin()
        try {
            val p = Student()
            p.name = "my user"
            em.persist(p)
            em.transaction.commit()
        } catch (t: Throwable) {
            em.transaction.rollback()
            throw t
        }

        a.analyze()
        component.f1()
        component.f2()

        evt.fire(MyEvent())
    }

    open fun listen(@Observes my: MyEvent) {
        println("event: $my")
    }
}