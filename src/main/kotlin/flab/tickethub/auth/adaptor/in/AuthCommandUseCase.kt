package flab.tickethub.auth.adaptor.`in`

import flab.tickethub.auth.domain.TokenPair
import flab.tickethub.auth.domain.TokenPayload

interface AuthCommandUseCase {

    fun updateRefreshToken(tokenPayload: TokenPayload): TokenPair

}
