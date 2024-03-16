package flab.tickethub.member.application.port.out

import flab.tickethub.member.domain.Member

interface MemberCommandPort {

    fun create(member: Member)

}
