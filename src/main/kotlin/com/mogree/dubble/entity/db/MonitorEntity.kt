package com.mogree.dubble.entity.db

import javax.persistence.*

@Entity
@Table(name = "monitor")
data class MonitorEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var internalPageTitle: String? = null,
        var productId: Long? = null,
        var contactId: Long? = null,
        var userId: Long? = null,
        var customerId: Long? = null,
        var sender: String? = null,
        var receiver: String? = null,
        var email: String? = null,
        var phone: String? = null,
        var sendingDate: String? = null,
        var sentStatus: String? = null,
        var jobId: String? = null,
        var jobGroup: String? = null
)