package flab.tickethub.support.config

import flab.tickethub.support.properties.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(JwtProperties::class)
@Configuration
class PropertiesConfig
