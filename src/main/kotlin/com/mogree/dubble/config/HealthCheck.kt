package com.mogree.dubble.config

import com.mogree.dubble.storage.repository.UserRepository
import com.mogree.spring.exception.APIInternalServerException
import com.mogree.spring.response.StatusResponse
import com.mogree.spring.service.IHealthChecker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
@Primary
class HealthCheck @Autowired constructor(
    private val userRepository: UserRepository
) : IHealthChecker {


    override fun healthy(): StatusResponse {
        if (userRepository.count() < 0) {
            throw APIInternalServerException(Config.ErrorMessagesGerman.DB_CONNECTION)
        }
        return StatusResponse(HttpStatus.OK);
    }
}
