package flab.tickethub.auth.application.port.out

import flab.tickethub.auth.domain.TokenPair
import flab.tickethub.auth.domain.TokenPayload

interface TokenProvider {

    fun generateTokenPair(tokenPayload: TokenPayload): TokenPair

    fun generateAccessToken(tokenPayload: TokenPayload): String

    fun generateRefreshToken(tokenPayload: TokenPayload): String

    fun validateAccessToken(token: String?): TokenPayload

    fun validateRefreshToken(token: String?): TokenPayload

}
