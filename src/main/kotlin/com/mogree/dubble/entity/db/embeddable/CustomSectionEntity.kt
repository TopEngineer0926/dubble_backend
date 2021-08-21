package com.mogree.dubble.entity.db.embeddable

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CustomSectionEntity : Serializable {

    @Column(name = "headline", nullable = false)
    lateinit var headline: String

    @Column(name = "link", nullable = true)
    var link: String? = null

    @Column(name = "button_text", nullable = true)
    var buttonText: String? = null

    @Column(name = "custom_text", nullable = true)
    var customText: String? = null

}
