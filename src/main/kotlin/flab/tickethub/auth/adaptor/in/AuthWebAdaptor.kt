package flab.tickethub.auth.adaptor.`in`

import flab.tickethub.auth.adaptor.`in`.request.LoginRequest
import flab.tickethub.auth.adaptor.`in`.response.LoginResponse
import flab.tickethub.auth.application.port.`in`.AuthQueryUseCase
import flab.tickethub.support.endpoint.AUTH_URL
import flab.tickethub.support.endpoint.LOGIN_ENDPOINT
import flab.tickethub.support.response.ApiResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(AUTH_URL)
@RestController
class AuthWebAdaptor(private val authQueryUseCase: AuthQueryUseCase) {

    @PostMapping(LOGIN_ENDPOINT)
    fun login(@RequestBody request: LoginRequest): ApiResult<LoginResponse> {
        val tokenPair = authQueryUseCase.login(request)

        return ApiResult.ok(
            LoginResponse.from(tokenPair)
        )
    }

}