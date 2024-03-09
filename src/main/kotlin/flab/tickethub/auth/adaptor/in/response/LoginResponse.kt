package flab.tickethub.auth.adaptor.`in`.response

import flab.tickethub.auth.domain.TokenPair

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String

) {
    companion object {
        fun from(tokenPair: TokenPair): LoginResponse {
            return LoginResponse(
                accessToken = tokenPair.accessToken,
                refreshToken = tokenPair.refreshToken
            )
        }
    }
}
