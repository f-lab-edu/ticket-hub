package flab.tickethub.auth.adaptor.`in`

import flab.tickethub.auth.adaptor.`in`.request.LoginRequest
import flab.tickethub.auth.adaptor.`in`.request.RefreshAccessTokenRequest
import flab.tickethub.auth.adaptor.`in`.response.RefreshAccessTokenResponse
import flab.tickethub.auth.application.port.`in`.AuthCommandUseCase
import flab.tickethub.auth.domain.MemberPrincipal
import flab.tickethub.auth.domain.TokenPair
import flab.tickethub.support.constant.ApiEndpoint
import flab.tickethub.support.response.ApiResult
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(ApiEndpoint.AUTH)
@RestController
class AuthWebAdaptor(
    private val authCommandUseCase: AuthCommandUseCase,
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest)
            : ResponseEntity<ApiResult<TokenPair>> {
        val tokenPair = authCommandUseCase.login(request)

        return ApiResult.ok(tokenPair)
    }

    @PostMapping("/logout")
    fun logout(@AuthenticationPrincipal memberPrincipal: MemberPrincipal)
            : ResponseEntity<Unit> {
        authCommandUseCase.logout(memberPrincipal)

        return ApiResult.ok()
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(@Valid @RequestBody request: RefreshAccessTokenRequest)
            : ResponseEntity<ApiResult<RefreshAccessTokenResponse>> {
        val accessToken = authCommandUseCase.refreshAccessToken(request)

        return ApiResult.ok(RefreshAccessTokenResponse(accessToken))
    }

}
