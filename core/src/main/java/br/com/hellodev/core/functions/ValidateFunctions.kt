package br.com.hellodev.core.functions

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    return email.matches(emailRegex.toRegex())
}

fun isValidName(name: String): Boolean {
    val regex = "^[a-zA-Z]{3,}$".toRegex()
    return regex.matches(name)
}

fun isValidSurname(surname: String): Boolean {
    val regex = "^[a-zA-Z]{3,}$".toRegex()
    return regex.matches(surname)
}

fun isValidFullName(fullName: String): Boolean {
    val parts = fullName.trim().split(" ".toRegex())
    return parts.size >= 2 && parts.all { it.length >= 3 }
}


fun isValidPhone(phone: String): Boolean {
    val regex = """^\d{2}9\d{8}$""".toRegex()
    return regex.matches(phone)
}