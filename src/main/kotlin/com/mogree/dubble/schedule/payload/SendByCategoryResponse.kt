package com.mogree.dubble.schedule.payload

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_NULL)
class SendByCategoryResponse {
    var isSuccess: Boolean
    var message: String

    constructor(success: Boolean, message: String) {
        isSuccess = success
        this.message = message
    }

}