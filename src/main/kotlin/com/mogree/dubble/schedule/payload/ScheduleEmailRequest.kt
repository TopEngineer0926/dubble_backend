package com.mogree.dubble.schedule.payload

import com.mogree.server.gen.model.CustomerModel
import java.time.LocalDateTime
import java.time.ZoneId


class ScheduleEmailRequest {

    var productId: Long? = null

    var customer: CustomerModel? = null

    var dateTime: LocalDateTime? = null

    var timeZone: ZoneId? = null

}