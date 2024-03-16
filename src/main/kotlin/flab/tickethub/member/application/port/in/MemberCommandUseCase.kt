package flab.tickethub.member.application.port.`in`

import flab.tickethub.member.adaptor.`in`.request.CreateMemberRequest

interface MemberCommandUseCase {

    fun create(request: CreateMemberRequest)

}
