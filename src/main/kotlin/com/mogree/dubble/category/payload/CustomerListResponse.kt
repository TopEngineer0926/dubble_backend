package com.mogree.dubble.category.payload

import com.fasterxml.jackson.annotation.JsonInclude
import com.mogree.server.gen.model.CustomerModel


@JsonInclude(JsonInclude.Include.NON_NULL)
class CustomerListResponse {
    var offset: Int? = 0
    var list: List<CustomerModel>? = null
    var limit: Int? = 30
    var overallsize: Int? = 0

    constructor(offset: Int, list: List<CustomerModel>, limit: Int, overallsize: Int) {
        this.offset = offset
        this.list = list
        this.limit = limit
        this.overallsize = overallsize
    }

}