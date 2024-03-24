package flab.tickethub.auth.adaptor.`in`.request

import jakarta.validation.constraints.NotBlank

data class RefreshAccessTokenRequest(
    @field:NotBlank
    val refreshToken: String
)
