package com.mogree.dubble.entity.db

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.EntityAudition
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = Config.Database.TABLE_MEDIA)
class MediaEntity : EntityAudition() {

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Int = 0

    @Column(name = "foreign_id")
    var foreignId: Int = 0

    @Column(name = "foreign_table")
    var foreignTable: String = ""

    @Column(name = "media_type")
    var mediaType: Int = 0

    @Column(name = "file_name")
    var fileName: String = ""

    @Column(name = "path")
    var path: String = ""

    @Column(name = "user_id")
    var userid: Long = 0

    @Column(name = "title")
    var title: String = ""

    @Column(name = "display_order")
    var order: Int? = null

    @Column(name = "status")
    var status: String = ""

}
