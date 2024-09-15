package br.com.logistic.operations.user_service.core.entities

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.UuidGenerator
import java.util.*

@MappedSuperclass
abstract class PersistentObject {

    @Id
    @Column(name ="ID", nullable = false, updatable = false)
    @UuidGenerator
    var id: UUID? = null
}
