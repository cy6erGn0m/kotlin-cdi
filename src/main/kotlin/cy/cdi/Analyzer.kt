package cy.cdi

import javax.inject.*

interface Analyzer {
    fun analyze()
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class DefaultAnalyzer

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class SpecializedAnalyzer

@SpecializedAnalyzer
class SpecializedAnalyzerImpl : Analyzer {
    override fun analyze() {
        println("specialized")
    }
}

@Singleton
@DefaultAnalyzer
class DefaultAnalyzerImpl : Analyzer {
    override fun analyze() {
        println("default one")
    }
}
