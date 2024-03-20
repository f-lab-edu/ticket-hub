package flab.tickethub.auth.domain

import flab.tickethub.support.domain.Identifiable

data class TokenPair(
    @field:Transient val memberId: Identifiable,
    val accessToken: String,
    val refreshToken: String
)
