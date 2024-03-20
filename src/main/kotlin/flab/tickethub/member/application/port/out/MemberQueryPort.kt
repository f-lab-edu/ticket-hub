package flab.tickethub.member.application.port.out

import flab.tickethub.member.domain.Member

interface MemberQueryPort {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): Member?

    fun findById(memberId: Long): Member?

}
