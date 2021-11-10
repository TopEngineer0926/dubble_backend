package com.mogree.dubble.entity.db

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.EntityAudition
import javax.persistence.*

/**
 * Contact entity class (employees).
 */
@Entity
@Table(name = Config.Database.TABLE_CONTACT)
class ContactEntity : EntityAudition() {

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "first_name", nullable = false)
    lateinit var firstName: String

    @Column(name = "last_name", nullable = false)
    lateinit var lastName: String

    @Column(name = "email", nullable = false)
    lateinit var email: String

    @Column(name = "phone_number", nullable = false)
    lateinit var phoneNumber: String

    @Column(name = "abbreviation", nullable = true)
    var abbreviation: String? = null

    @Column(name = "invite_status", nullable = true)
    var inviteStatus: Int? = null

    /* ***** Relations ***** */

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = ForeignKey(name = "fk_contact__user"))
    lateinit var user: UserEntity

}
