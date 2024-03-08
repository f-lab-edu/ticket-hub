package flab.tickethub.member.adaptor.`in`

import flab.tickethub.member.adaptor.`in`.request.CreateMemberRequest
import flab.tickethub.support.RestDocsSupport
import flab.tickethub.support.endpoint.MEMBER_URL
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.net.URI

class MemberWebAdaptorTest : RestDocsSupport() {

    @DisplayName("회원 가입")
    @Test
    fun `success create member`() {
        val request = CreateMemberRequest(
            email = "email@email.com",
            password = "password",
            name = "name",
            phoneNumber = "nickname"
        )

        mockMvc.perform(
            post(URI.create(MEMBER_URL))
                .content(convert(request))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated)
    }

    override fun controller(): Any {
        return MemberWebAdaptor()
    }

}
