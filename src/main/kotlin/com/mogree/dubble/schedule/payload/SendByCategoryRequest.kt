package com.mogree.dubble.schedule.payload

import com.mogree.dubble.entity.db.CustomerEntity
import com.mogree.server.gen.model.CustomerModel

class SendByCategoryRequest {

    var productId: Long? = null

    var customer: CustomerModel? = null
}