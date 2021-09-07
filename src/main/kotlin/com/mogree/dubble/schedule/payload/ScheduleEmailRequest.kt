package com.mogree.dubble.schedule.payload

import java.time.LocalDateTime
import java.time.ZoneId


class ScheduleEmailRequest {

    var productId: Long? = null

    var dateTime: LocalDateTime? = null

    var timeZone: ZoneId? = null

}