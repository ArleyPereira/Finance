package br.com.hellodev.core.enums.sheet

enum class SheetType(val type: String) {
    EMPTY("EMPTY"),
    GENERIC("GENERIC"),
    SELECT_CLOSING_DAY("SELECT_CLOSING_DAY"),
    SELECT_DUE_DAY("SELECT_DUE_DAY"),
    LOGOUT("LOGOUT"),
    THEME("THEME")
}