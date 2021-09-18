package com.mogree.dubble.service.monitor.payload

import com.fasterxml.jackson.annotation.JsonInclude
import com.mogree.dubble.entity.db.MonitorEntity


@JsonInclude(JsonInclude.Include.NON_NULL)
class MonitorListResponse {
    var offset: Int? = 0
    var list: List<MonitorEntity>? = null
    var limit: Int? = 30
    var overallsize: Int? = 0

    constructor(offset: Int, list: List<MonitorEntity>, limit: Int, overallsize: Int) {
        this.offset = offset
        this.list = list
        this.limit = limit
        this.overallsize = overallsize
    }

}