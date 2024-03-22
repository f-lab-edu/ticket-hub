package flab.tickethub.auth.adaptor.`in`

import flab.tickethub.auth.domain.MemberPrincipal
import flab.tickethub.auth.domain.TokenPair
import flab.tickethub.auth.domain.TokenPayload

interface AuthCommandUseCase {

    fun updateRefreshToken(tokenPayload: TokenPayload): TokenPair

    fun logout(memberPrincipal: MemberPrincipal)

}
