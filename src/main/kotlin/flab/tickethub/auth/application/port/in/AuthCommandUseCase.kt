package flab.tickethub.auth.application.port.`in`

import flab.tickethub.auth.adaptor.`in`.request.LoginRequest
import flab.tickethub.auth.adaptor.`in`.request.RefreshAccessTokenRequest
import flab.tickethub.auth.domain.MemberPrincipal
import flab.tickethub.auth.domain.TokenPair

interface AuthCommandUseCase {

    fun login(request: LoginRequest): TokenPair

    fun logout(memberPrincipal: MemberPrincipal)

    fun refreshAccessToken(request: RefreshAccessTokenRequest): String

}
