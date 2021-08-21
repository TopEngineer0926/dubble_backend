package com.mogree.dubble

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = ["com.mogree"])
@EntityScan(basePackages = ["com.mogree.dubble.entity", "com.mogree.jwt.cache.database"])
@EnableJpaRepositories(basePackages = ["com.mogree.dubble.storage.repository", "com.mogree.jwt.cache.database"])
class DubbleApplication

fun main(args: Array<String>) {
    runApplication<DubbleApplication>(*args)
}
