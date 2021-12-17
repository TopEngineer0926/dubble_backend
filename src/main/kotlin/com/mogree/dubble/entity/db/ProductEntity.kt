package com.mogree.dubble.entity.db

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.EntityAudition
import com.mogree.dubble.entity.db.embeddable.CustomSectionEntity
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(
        name = Config.Database.TABLE_PRODUCT,
        uniqueConstraints = [
            UniqueConstraint(
                    columnNames = arrayOf("share_code"),
                    name = "uc_product__share_code"
            )
        ]
)
class ProductEntity : EntityAudition() {

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "headline", nullable = false)
    lateinit var headline: String

    @Column(name = "greeting", nullable = false)
    lateinit var greeting: String

    @Column(name = "custom_text", nullable = false, columnDefinition = "longtext")
    lateinit var customText: String

    @Enumerated(value = EnumType.STRING)
    @Column(name = "publication_status", nullable = false)
    lateinit var publicationStatus: Config.ProductPublicationStatuses

    @Column(name = "review_section_name", nullable = true)
    var reviewSectionName: String? = null

    @Column(name = "press_info_section_name", nullable = true)
    var pressInfoSectionName: String? = null

    @Column(name = "share_code", nullable = false, updatable = false)
    lateinit var shareCode: String

    @Column(name = "internal_page_title", nullable = false)
    lateinit var internalPageTitle: String

    @Column(name = "video_section_headline", nullable = true)
    var videoSectionHeadline: String? = null

    @Column(name = "template", nullable = true)
    var template: String? = null

    @Column(name = "mail_headline", nullable = true)
    var mailHeadline: String? = null

    @Column(name = "mail_textline", nullable = true)
    var mailTextline: String? = null

    /* ***** Relations ***** */

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            updatable = false,
            foreignKey = ForeignKey(name = "fk_product__user")
    )
    lateinit var user: UserEntity

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false, foreignKey = ForeignKey(name = "fk_product__contact"))
    lateinit var contact: ContactEntity

    // is not required
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true, foreignKey = ForeignKey(name = "fk_product__customer"))
    var customer: CustomerEntity? = null

    @ElementCollection
    @CollectionTable(
            name = Config.Database.TABLE_PRODUCT_REVIEW,
            joinColumns = [JoinColumn(
                    name = "product_id",
                    foreignKey = ForeignKey(name = "fk_product_review__product")
            )]
    )
    // if needed additional fields - CustomSectionEntity can be extended by new object (ReviewSectionEntity)
    var reviews: List<CustomSectionEntity> = ArrayList()

    @ElementCollection
    @CollectionTable(
            name = Config.Database.TABLE_PRODUCT_PRESS_INFO,
            joinColumns = [JoinColumn(
                    name = "product_id",
                    foreignKey = ForeignKey(name = "fk_product_press_info__product")
            )]
    )
    // if needed additional fields - CustomSectionEntity can be extended by new object (PressInfoSectionEntity)
    var pressInfos: List<CustomSectionEntity> = ArrayList()

}
