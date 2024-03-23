package flab.tickethub.auth.application.port.`in`

import flab.tickethub.auth.adaptor.`in`.request.RefreshAccessTokenRequest
import flab.tickethub.auth.domain.MemberPrincipal
import flab.tickethub.auth.domain.TokenPair
import flab.tickethub.auth.domain.TokenPayload

interface AuthCommandUseCase {

    fun logout(memberPrincipal: MemberPrincipal)

    fun updateRefreshToken(tokenPayload: TokenPayload): TokenPair

    fun refreshAccessToken(request: RefreshAccessTokenRequest): String

}
