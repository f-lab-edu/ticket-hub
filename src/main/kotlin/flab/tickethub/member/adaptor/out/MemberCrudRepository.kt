package flab.tickethub.member.adaptor.out

import flab.tickethub.member.domain.Member
import org.springframework.data.repository.CrudRepository

interface MemberCrudRepository : CrudRepository<Member, Long> {

    fun existsByEmail(email: String): Boolean

}