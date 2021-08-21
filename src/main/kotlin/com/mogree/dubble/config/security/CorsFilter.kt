package com.mogree.dubble.config.security

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * This filter enables cors but only when certain conditions are met.
 * It has to be used as @Component (or as @Bean) and provided to the WebSecurityConfig.
 * (Without spring security there are other approaches for cors.)
 *
 *
 * In this case the filter is independent from the spring security filter chain because the default
 * spring filter chain scans for @Components first. Every single request passes through this filter.
 * The request are handled by the servlets that are called after the filter.
 *
 *
 * This only works because all OPTIONS requests are ignored in the spring security filter chain via a custom
 * implementation of the WebSecurityConfigurerAdapter -> web.ignoring().antMatchers(HttpMethod.OPTIONS, "/ **");
 * This alone is not enough because without any handler the cors headers are set but there is no
 * status code returned (or specifically 400 - Bad Request) which leads to a access-control-fail in
 * the browser. So the only solution is to check for OPTIONS requests in the filter and skip the
 * chain setting the code 200 manually.
 *
 *
 * The big advantage of this method is that it doesn't matter which endpoint and if it is secured
 * or not. It should always add the headers and always return 200-OK on OPTIONS requests and
 * therefore, enable CORS globally.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class CorsFilter : Filter {

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig?) {
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val response = servletResponse as HttpServletResponse
        val request = servletRequest as HttpServletRequest

        //careful! if the credentials header is set it, the origin cannot be a wildcard.
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS")
        response.setHeader("Access-Control-Allow-Headers",
            "Access-Control-Allow-Origin,Authorization,role,no-spinner,Access-Control-Allow-Origin,Access-Control-Allow-Headers,access-control-allow-methods,mogree-Password,mogree-password-old,mogree-username,mogree-Mail,Content-Language,mogree-Unique-Device-Id,mogree-Access-Id,mogree-Access-Token,mogree-Platform-Id,mogree-Client-Id,mogree-App-Version,Accept,Accept-Encoding,Accept-Language,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,Connection,Content-Type,Host,Origin,Referer,Token-Id,User-Agent, X-Requested-With,X-Anyidea-Device-Id,AUTHORITY_ID,session-id,if-modified-since,cache-control,pragma,Access-Control-Allow-Origin"
        )

        if ("OPTIONS".equals(request.method, ignoreCase = true)) {
            response.status = HttpServletResponse.SC_OK //set 200 always on OPTIONS
        } else {
            filterChain.doFilter(servletRequest, servletResponse) //let the sevlets handle the resp.
        }
    }

    override fun destroy() {}

}
