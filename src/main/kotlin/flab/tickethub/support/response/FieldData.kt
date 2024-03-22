package flab.tickethub.support.response

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.validation.FieldError

@JsonPropertyOrder("fieldName", "value", "reason")
data class FieldData private constructor(
    val fieldName: String,
    val value: String,
    val reason: String
) {
    companion object {
        fun of(it: FieldError) =
            FieldData(
                fieldName = it.field,
                value = it.rejectedValue?.toString().orEmpty(),
                reason = it.defaultMessage.orEmpty()
            )
    }
}