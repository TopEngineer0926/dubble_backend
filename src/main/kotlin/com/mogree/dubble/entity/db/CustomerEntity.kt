package com.mogree.dubble.entity.db

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.EntityAudition
import javax.persistence.*

@Entity
@Table(name = Config.Database.TABLE_CUSTOMER)
class CustomerEntity : EntityAudition() {

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "first_name", nullable = false)
    lateinit var firstname: String

    @Column(name = "last_name", nullable = false)
    lateinit var lastname: String

    @Column(name = "email", nullable = true)
    var email: String? = null

    @Column(name = "phone_number", nullable = true)
    var phoneNumber: String? = null

    @Column(name = "customer_number", nullable = true)
    var customerNumber: String? = null

    @Column(name = "academic_degree_preceding", nullable = true)
    var academicDegreePreceding: String? = null

    @Column(name = "academic_degree_subsequent", nullable = true)
    var academicDegreeSubsequent: String? = null

    @Column(name = "category", nullable = true)
    var category: String? = null

    @Column(name = "company_name", nullable = true)
    var companyName: String? = null

    @Column(name = "domain_name", nullable = true)
    var domainName: String? = null

    /* ***** Relations ***** */

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = ForeignKey(name = "fk_customer__user"))
    lateinit var user: UserEntity

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private var products: List<ProductEntity> = ArrayList()

    @PreRemove
    private fun preRemove() {
        for (product in products) {
            product.customer = null
        }
    }

}
