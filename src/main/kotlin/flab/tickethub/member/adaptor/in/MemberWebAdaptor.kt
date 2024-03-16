package flab.tickethub.member.adaptor.`in`

import flab.tickethub.member.adaptor.`in`.request.CreateMemberRequest
import flab.tickethub.member.application.port.`in`.MemberCommandUseCase
import flab.tickethub.support.constant.ApiEndpoint
import flab.tickethub.support.response.ApiResult
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(ApiEndpoint.MEMBER)
@RestController
class MemberWebAdaptor(
    private val memberCommandUseCase: MemberCommandUseCase
) {

    @PostMapping
    fun create(@Valid @RequestBody request: CreateMemberRequest): ResponseEntity<Unit> {
        memberCommandUseCase.create(request)
        return ApiResult.created()
    }

}
