package flab.tickethub.member.adaptor.`in`.request

import flab.tickethub.support.constant.EMAIL_MESSAGE
import flab.tickethub.support.constant.EMAIL_REGEXP
import flab.tickethub.support.constant.PHONE_NUMBER_MESSAGE
import flab.tickethub.support.constant.PHONE_NUMBER_REGEXP
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class CreateMemberRequest(
    @field:Pattern(
        regexp = EMAIL_REGEXP,
        message = EMAIL_MESSAGE
    )
    @field:NotBlank
    val email: String,
    @field:NotBlank
    val password: String,
    @field:NotBlank
    val name: String,
    @field:Pattern(
        regexp = PHONE_NUMBER_REGEXP,
        message = PHONE_NUMBER_MESSAGE
    )
    @field:NotBlank
    val phoneNumber: String
)
