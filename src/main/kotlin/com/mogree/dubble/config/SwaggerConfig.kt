package com.mogree.dubble.config

import io.swagger.models.auth.In
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
@Profile("local", "dev")
class SwaggerConfig {

    @Value("\${buildNr}")
    private lateinit var buildNr: String

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("Dubble API").description("Backend for Dubble.")
                .version(buildNr).contact(Contact("", "", "support@mogree.com")).build()
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java)).paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .securitySchemes(listOf(ApiKey("Bearer", HttpHeaders.AUTHORIZATION, In.HEADER.name)))
                .securityContexts(listOf(securityContext()))
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build()
    }

    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScopes = arrayOf(AuthorizationScope("global", "accessEverything"))
        return listOf(SecurityReference("Bearer", authorizationScopes))
    }

}
