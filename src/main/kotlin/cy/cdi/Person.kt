package cy.cdi

import javax.persistence.*
import javax.validation.constraints.*

enum class Degree {
    PhD
}

@Entity
@Table(name = "groups")
class Group {
    @Id
    @GeneratedValue
    var id: Int = 0

    @Column(nullable = false, unique = true)
    var name: String = ""
}

@MappedSuperclass
@DiscriminatorColumn(name = "KIND", discriminatorType = DiscriminatorType.CHAR)
@Inheritance
abstract class Person {
    @Id
    @GeneratedValue
    var id: Int = 0

    @Column(nullable = false, length = 100)
    @Size(min = 2, max = 100, message = "Name length")
    var name: String = ""
}

@Entity
@DiscriminatorValue("S")
class Student : Person() {
    @ManyToOne(optional = true)
    var group: Group? = null
}

@Entity
@DiscriminatorValue("T")
class Teacher :  Person() {
    @Enumerated(EnumType.STRING)
    var degree: Degree? = null
}