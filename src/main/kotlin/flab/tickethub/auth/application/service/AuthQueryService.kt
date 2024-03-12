package flab.tickethub.auth.application.service

import flab.tickethub.auth.adaptor.`in`.request.LoginRequest
import flab.tickethub.auth.application.port.`in`.AuthQueryUseCase
import flab.tickethub.auth.domain.TokenPair
import org.springframework.stereotype.Service

@Service
class AuthQueryService : AuthQueryUseCase {
    override fun login(request: LoginRequest): TokenPair {
        return TokenPair(
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )
    }

}