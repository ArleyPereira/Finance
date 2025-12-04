package br.com.hellodev.design.functions

import androidx.compose.ui.graphics.Color

fun getColorFromInitials(firstName: String?, lastName: String?): Color {
    val colorsMap = mapOf(
        'A' to Color(0xFFE57373),
        'B' to Color(0xFFF06292),
        'C' to Color(0xFFBA68C8),
        'D' to Color(0xFF64B5F6),
        'E' to Color(0xFF4DB6AC),
        'F' to Color(0xFF81C784),
        'G' to Color(0xFFAED581),
        'H' to Color(0xFFFFD54F),
        'I' to Color(0xFFFF8A65),
        'J' to Color(0xFFA1887F),
        'K' to Color(0xFF90A4AE),
        'L' to Color(0xFFE0E0E0),
        'M' to Color(0xFF7986CB),
        'N' to Color(0xFF00ACC1),
        'O' to Color(0xFF00897B),
        'P' to Color(0xFF43A047),
        'Q' to Color(0xFF558B2F),
        'R' to Color(0xFFFFC107),
        'S' to Color(0xFFFFA000),
        'T' to Color(0xFFFF7043),
        'U' to Color(0xFF6D4C41),
        'V' to Color(0xFF455A64),
        'W' to Color(0xFF37474F),
        'X' to Color(0xFFD32F2F),
        'Y' to Color(0xFF7B1FA2),
        'Z' to Color(0xFF303F9F)
    )

    val firstInitial = firstName?.firstOrNull()?.uppercaseChar()
    val lastInitial = lastName?.firstOrNull()?.uppercaseChar()

    return when {
        firstInitial != null && lastInitial != null -> {
            val combinedHash = (firstInitial.code + lastInitial.code) % colorsMap.size
            colorsMap.values.elementAt(combinedHash)
        }

        firstInitial != null -> colorsMap[firstInitial] ?: Color.Gray
        lastInitial != null -> colorsMap[lastInitial] ?: Color.Gray
        else -> Color.Gray
    }
}