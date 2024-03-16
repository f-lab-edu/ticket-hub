package flab.tickethub.member.adaptor.`in`.request

import flab.tickethub.support.constant.Message
import flab.tickethub.support.constant.Regexp
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class CreateMemberRequest(
    @field:Pattern(
        regexp = Regexp.EMAIL,
        message = Message.EMAIL
    )
    @field:NotBlank
    val email: String,
    @field:NotBlank
    val password: String,
    @field:NotBlank
    val name: String,
    @field:Pattern(
        regexp = Regexp.PHONE_NUMBER,
        message = Message.PHONE_NUMBER
    )
    @field:NotBlank
    val phoneNumber: String
)
