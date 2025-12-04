package br.com.hellodev.core.functions.string

/**
 * Retorna o nome completo do usuário a partir do primeiro e do último nome
 * Entrada: Arley, null, Arley, Santana
 * Saida: Arley, Arley Santana
 */
fun getFullName(firstName: String?, lastName: String?): String {
    return if (firstName != null && lastName != null) {
        "$firstName $lastName"
    } else firstName ?: ""
}

/**
 * Retorna as iniciais do nome e sobrenome
 * Entrada: Arley Santana, Alisson Santana
 * Saida: AS, AS
 */
fun getInitials(firstName: String?, lastName: String?): String {
    val firstInitial = firstName?.firstOrNull()?.uppercaseChar()?.toString() ?: ""
    val lastInitial = lastName?.firstOrNull()?.uppercaseChar()?.toString() ?: ""
    return firstInitial + lastInitial
}