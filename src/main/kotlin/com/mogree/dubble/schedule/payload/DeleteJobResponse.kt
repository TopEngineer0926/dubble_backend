package com.mogree.dubble.schedule.payload

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_NULL)
class DeleteJobResponse {
    var isSuccess: Boolean
    var message: String

    constructor(success: Boolean, message: String) {
        isSuccess = success
        this.message = message
    }

}