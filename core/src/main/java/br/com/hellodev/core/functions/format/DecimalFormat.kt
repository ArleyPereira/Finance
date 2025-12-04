package br.com.hellodev.core.functions.format

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

object DecimalFormat {
    fun format(value: Float?, removeSymbol: Boolean = false): String {
        val moneyFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        moneyFormatter.currency = Currency.getInstance("BRL")
        return if (removeSymbol) {
            moneyFormatter.format(value ?: 0f).replace("R\$ ", "")
        } else {
            moneyFormatter.format(value ?: 0f)
        }
    }
}