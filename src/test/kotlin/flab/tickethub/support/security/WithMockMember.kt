package flab.tickethub.support.security

import flab.tickethub.member.domain.Role
import org.springframework.security.test.context.support.WithSecurityContext

@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = WithMockMemberSecurityContextFactory::class)
annotation class WithMockMember(
    val id: Long = 1L,
    val role: Role = Role.BUYER
)
