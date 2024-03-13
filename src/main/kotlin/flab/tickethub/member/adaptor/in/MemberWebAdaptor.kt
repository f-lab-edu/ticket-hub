package flab.tickethub.member.adaptor.`in`

import flab.tickethub.member.adaptor.`in`.request.CreateMemberRequest
import flab.tickethub.member.application.port.`in`.MemberCommandUseCase
import flab.tickethub.support.endpoint.MEMBER_URL
import flab.tickethub.support.response.ApiResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(MEMBER_URL)
@RestController
class MemberWebAdaptor(
    private val memberCommandUseCase: MemberCommandUseCase
) {

    @PostMapping
    fun create(@RequestBody request: CreateMemberRequest): ApiResult<Unit> {
        memberCommandUseCase.create(request)
        return ApiResult.created()
    }

}
