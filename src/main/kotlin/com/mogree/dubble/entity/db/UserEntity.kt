package com.mogree.dubble.entity.db

import com.fasterxml.jackson.annotation.JsonIgnore
import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.EntityAudition
import javax.persistence.*

@Entity
@Table(
    name = Config.Database.TABLE_USER,
    uniqueConstraints = [
        UniqueConstraint(
            columnNames = arrayOf("email"),
            name = "uc_user__email"
        )
    ]
)
class UserEntity : EntityAudition() {

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long = 0

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    lateinit var role: Config.Roles

    @Column(name = "first_name", nullable = false)
    lateinit var firstName: String

    @Column(name = "last_name", nullable = false)
    lateinit var lastName: String

    @Column(name = "email", nullable = false)
    lateinit var email: String

    @JsonIgnore
    @Column(name = "password", nullable = false)
    lateinit var password: String

    @Column(name = "company_name", nullable = true)
    var companyName: String? = null

    @Column(name = "main_color", nullable = true)
    var mainColor: String? = null

    @Column(name = "secondary_color", nullable = true)
    var secondaryColor: String? = null

    @Column(name = "is_activated", nullable = false)
    var activated: Boolean = false

    @JsonIgnore
    @Column(name = "activation_code", nullable = true)
    var activationCode: String? = null

    @JsonIgnore
    @Column(name = "password_reset_code", nullable = true)
    var passwordResetCode: String? = null

    @Enumerated(value = EnumType.STRING)
    @Column(name = "logo_position", nullable = true)
    var logoPosition: Config.LogoPosition? = null

    @Column(name = "contact_button_color", nullable = true)
    var contactButtonColor: String? = null

    @Column(name = "category", nullable = true)
    var category: String? = null

    @Column(name = "template", nullable = true)
    var template: String? = null

    @Column(name = "master_id", nullable = true)
    var masterId: Long? = null

    @Column(name = "mail_headline", nullable = true)
    var mailHeadline: String? = null

    @Column(name = "mail_textline", nullable = true)
    var mailTextline: String? = null

}
