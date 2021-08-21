package com.mogree.dubble.config.component

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * Class for creating beans.
 */
@Configuration
class BeanComponent {

    @Bean
    fun createEncoderBean(): PasswordEncoder { // create bean of the password encoder
        return BCryptPasswordEncoder()
    }

}
