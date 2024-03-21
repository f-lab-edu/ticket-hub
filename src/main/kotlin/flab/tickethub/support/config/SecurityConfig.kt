package flab.tickethub.support.config

import flab.tickethub.support.constant.ApiEndpoint
import flab.tickethub.support.security.TokenAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val tokenAuthenticationFilter: TokenAuthenticationFilter
) {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        httpSecurity
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }

        httpSecurity
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        HttpMethod.POST,
                        ApiEndpoint.MEMBER,
                        ApiEndpoint.AUTH + ApiEndpoint.LOGIN_ENDPOINT
                    )
                    .permitAll()
                it.anyRequest().authenticated()
            }

        httpSecurity
            .addFilterBefore(
                tokenAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )

        return httpSecurity.build()
    }

    @Bean
    fun passwordEncoder(): Argon2PasswordEncoder =
        Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()

}