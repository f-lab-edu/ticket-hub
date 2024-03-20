package flab.tickethub.member.adaptor.out

import flab.tickethub.member.application.port.out.MemberCommandPort
import flab.tickethub.member.application.port.out.MemberQueryPort
import flab.tickethub.member.domain.Member
import org.springframework.stereotype.Component

@Component
class MemberPersistenceAdaptor(
    private val memberCrudRepository: MemberCrudRepository
) : MemberCommandPort,
    MemberQueryPort {

    override fun create(member: Member) {
        memberCrudRepository.save(member)
    }

    override fun existsByEmail(email: String): Boolean {
        return memberCrudRepository.existsByEmail(email)
    }

    override fun findByEmail(email: String): Member? {
        return memberCrudRepository.findByEmail(email)
    }

    override fun findById(memberId: Long): Member? {
        return memberCrudRepository.findById(memberId).get()
    }

}
