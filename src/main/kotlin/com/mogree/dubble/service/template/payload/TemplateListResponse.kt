package com.mogree.dubble.service.template.payload

import com.fasterxml.jackson.annotation.JsonInclude
import com.mogree.server.gen.model.ProductModel


@JsonInclude(JsonInclude.Include.NON_NULL)
class TemplateListResponse {
    var offset: Int? = 0
    var list: List<ProductModel>? = null
    var limit: Int? = 30
    var overallsize: Int? = 0

    constructor(offset: Int, list: List<ProductModel>, limit: Int, overallsize: Int) {
        this.offset = offset
        this.list = list
        this.limit = limit
        this.overallsize = overallsize
    }

}