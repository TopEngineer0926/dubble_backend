package com.mogree.dubble.config.security

import com.mogree.dubble.config.Config
import com.mogree.jwt.api.allowMetrics
import com.mogree.jwt.api.allowSwagger
import com.mogree.jwt.api.use
import com.mogree.jwt.filter.AuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig @Autowired constructor(
    private val tokenService: AppTokenService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.use(tokenService)
            .csrf().disable()
            .addFilterBefore(
                AuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter::class.java
            )
            .antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/admin/**")
            .hasAuthority(Config.Roles.ADMIN.name)
            .and()
            .authorizeRequests()
            .anyRequest()
            .hasAnyAuthority(Config.Roles.ADMIN.name, Config.Roles.USER.name)
    }

    override fun configure(web: WebSecurity) {
        //declare all paths that should skip the entire spring security filter chain
        //TODO: disable swagger on prod and stage
        web.allowSwagger().allowMetrics().ignoring()
            .antMatchers(HttpMethod.POST, "/account")
            .antMatchers(HttpMethod.PUT, "/account/login")
            .antMatchers(HttpMethod.PUT, "/account/activate")
            .antMatchers(HttpMethod.GET, "/product/{productShareCode}/page")
            .antMatchers(HttpMethod.PUT, "/account/password/reset", "/account/password/reset/update")
            .and()
            //this is very important for the cors-filter
            .ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
    }

}
