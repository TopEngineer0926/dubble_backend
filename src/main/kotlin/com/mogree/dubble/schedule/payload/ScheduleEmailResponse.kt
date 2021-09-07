package com.mogree.dubble.schedule.payload

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_NULL)
class ScheduleEmailResponse {
    var isSuccess: Boolean
    var jobId: String? = null
    var jobGroup: String? = null
    var message: String

    constructor(success: Boolean, message: String) {
        isSuccess = success
        this.message = message
    }

    constructor(success: Boolean, jobId: String?, jobGroup: String?, message: String) {
        isSuccess = success
        this.jobId = jobId
        this.jobGroup = jobGroup
        this.message = message
    }

}