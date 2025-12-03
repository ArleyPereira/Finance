import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormatSymbols
import java.util.Locale

class DecimalVisualTransformation(
    private val decimalFormatter: DecimalFormatter
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val inputText = text.text
        val formattedNumber = decimalFormatter.formatForVisual(inputText)

        val newText = AnnotatedString(formattedNumber)

        val offsetMapping = FixedCursorOffsetMapping(
            contentLength = inputText.length,
            formattedContentLength = formattedNumber.length
        )

        return TransformedText(newText, offsetMapping)
    }
}

private class FixedCursorOffsetMapping(
    private val contentLength: Int,
    private val formattedContentLength: Int,
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = formattedContentLength
    override fun transformedToOriginal(offset: Int): Int = contentLength
}

class DecimalFormatter {
    private val symbols = DecimalFormatSymbols.getInstance(Locale("pt", "BR"))
    private val thousandsSeparator = symbols.groupingSeparator
    private val decimalSeparator = symbols.decimalSeparator

    fun cleanup(input: String): String {
        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return "0"

        val sb = StringBuilder()
        var hasDecimalSep = false

        for (char in input) {
            if (char.isDigit()) {
                sb.append(char)
                continue
            }
            if (char == decimalSeparator && !hasDecimalSep && sb.isNotEmpty()) {
                sb.append(char)
                hasDecimalSep = true
            }
        }

        return sb.toString()
    }

    fun formatForVisual(input: String): String {
        val cleanInput = input.filter { it.isDigit() }
        val paddedInput = cleanInput.padStart(3, '0')
        val intPart = paddedInput.dropLast(2)
        val fractionPart = paddedInput.takeLast(2)

        val formattedIntPart = intPart.reversed().chunked(3)
            .joinToString(separator = thousandsSeparator.toString())
            .reversed()

        return "$formattedIntPart$decimalSeparator$fractionPart"
    }

    companion object {
        fun getValue(value: String): Float {
            val cleanString = value.filter { it.isDigit() }
            return if (cleanString.isNotEmpty()) {
                cleanString.toDouble().toFloat() / 100f
            } else {
                0f
            }
        }
    }

}