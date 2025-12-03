package br.com.hellodev.core.functions

import br.com.hellodev.core.R
import br.com.hellodev.core.enums.input.InputType
import br.com.hellodev.core.enums.input.InputType.EMAIL
import br.com.hellodev.core.enums.input.InputType.FIRST_NAME
import br.com.hellodev.core.enums.input.InputType.FULL_NAME
import br.com.hellodev.core.enums.input.InputType.LAST_NAME
import br.com.hellodev.core.enums.input.InputType.PHONE

fun capitalizeEachWord(input: String): String {
    return input.split(" ").joinToString(" ") { word ->
        word.lowercase().replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }
}

fun inputErrorMessage(type: InputType?): Int {
    return when (type) {
        FIRST_NAME -> R.string.error_first_name_invalid
        LAST_NAME -> R.string.error_surname_invalid
        FULL_NAME -> R.string.error_full_name_invalid
        EMAIL -> R.string.error_email_invalid
        PHONE -> R.string.error_phone_invalid
        else -> R.string.error_generic
    }
}