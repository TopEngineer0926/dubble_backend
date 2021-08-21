package com.mogree.dubble.util.extension

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

inline fun String?.isNullOrStringBlank(nullClause: () -> Nothing) {
    if (this.isNullOrBlank()) {
        nullClause()
    }
}

fun Any?.asJsonString(): String {
    return try {
        ObjectMapper().registerModule(JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .writeValueAsString(this)
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}
