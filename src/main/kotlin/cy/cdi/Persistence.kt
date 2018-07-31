package cy.cdi

import javax.enterprise.context.*
import javax.enterprise.inject.*
import javax.persistence.*

class Persistence {
    @Produces
    @Default
    @ApplicationScoped
    fun emf(): EntityManagerFactory = javax.persistence.Persistence.createEntityManagerFactory("h2")

    @Produces
    @Default
    @ApplicationScoped
    fun entityManager(emf: EntityManagerFactory): EntityManager {
        return emf.createEntityManager()
    }

    fun dispose(@Disposes em: EntityManager){
        em.close()
    }

    fun dispose(@Disposes emf: EntityManagerFactory) {
        emf.close()
    }
}