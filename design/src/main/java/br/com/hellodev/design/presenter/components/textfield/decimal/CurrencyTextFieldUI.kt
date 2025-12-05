package br.com.hellodev.design.presenter.components.textfield.decimal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.functions.format.DecimalFormat
import br.com.hellodev.design.presenter.components.icon.default.getDrawableIllustration
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun CurrencyTextFieldUI(
    modifier: Modifier = Modifier,
    value: Float,
    maxValue: Float = Float.MAX_VALUE,
    onValueChange: (Float) -> Unit,
    label: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    error: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    val initial = remember(value) { DecimalFormat.format(value, removeSymbol = false) }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = initial,
                selection = TextRange(initial.length)
            )
        )
    }

    LaunchedEffect(value) {
        val formatted = DecimalFormat.format(value, removeSymbol = false)
        if (formatted != textFieldValue.text) {
            textFieldValue = TextFieldValue(
                text = formatted,
                selection = TextRange(formatted.length)
            )
        }
    }

    val customTextSelectionColors = TextSelectionColors(
        handleColor = ColorScheme.colorScheme.defaultColor,
        backgroundColor = ColorScheme.colorScheme.alphaDefaultColor
    )

    val borderColor = when {
        isFocused && isError -> ColorScheme.colorScheme.alertColor
        isFocused -> ColorScheme.colorScheme.defaultColor
        isError -> ColorScheme.colorScheme.alertColor
        else -> ColorScheme.colorScheme.border.unselected
    }

    val backgroundColor = when {
        isFocused && isError -> ColorScheme.colorScheme.alertAlphaColor
        isFocused -> ColorScheme.colorScheme.alphaDefaultColor
        isError -> ColorScheme.colorScheme.alertAlphaColor
        else -> ColorScheme.colorScheme.textField.background
    }

    Column(modifier = modifier.fillMaxWidth()) {
        CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
            TextField(
                value = textFieldValue,
                onValueChange = { new ->
                    val digits = new.text.filter(Char::isDigit)
                    val cents = digits.toLongOrNull() ?: 0L
                    var newAmount = cents / 100f

                    if (newAmount > maxValue) {
                        newAmount = maxValue
                    }

                    val formatted = DecimalFormat.format(newAmount, removeSymbol = false)

                    textFieldValue = TextFieldValue(
                        text = formatted,
                        selection = TextRange(formatted.length)
                    )

                    onValueChange(newAmount)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }
                    .focusRequester(focusRequester),
                enabled = enabled,
                readOnly = readOnly,
                textStyle = TextStyle(fontFamily = UrbanistFamily),
                label = {
                    Text(
                        text = label,
                        style = if (isFocused) {
                            TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 11.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(700),
                                color = ColorScheme.colorScheme.defaultColor
                            )
                        } else {
                            TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 15.4.sp,
                                fontFamily = UrbanistFamily,
                                color = ColorScheme.colorScheme.textField.placeholder
                            )
                        }
                    )
                },
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = isError,
                singleLine = true,
                keyboardOptions = keyboardOptions,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = backgroundColor,
                    focusedContainerColor = backgroundColor,
                    disabledContainerColor = backgroundColor,
                    errorContainerColor = backgroundColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedTextColor = ColorScheme.colorScheme.textField.text,
                    focusedTextColor = ColorScheme.colorScheme.textField.text,
                    errorTextColor = ColorScheme.colorScheme.textField.text,
                    disabledTextColor = ColorScheme.colorScheme.textField.disabledText,
                    cursorColor = ColorScheme.colorScheme.defaultColor
                )
            )
        }

        if (isError && error.isNotBlank()) {
            Row(modifier = Modifier.padding(start = 16.dp, top = 6.dp)) {
                Icon(
                    painter = getDrawableIllustration(type = IllustrationType.IC_ALERT),
                    contentDescription = null,
                    tint = ColorScheme.colorScheme.alertColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = error,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = UrbanistFamily,
                        color = ColorScheme.colorScheme.alertColor,
                        letterSpacing = 0.2.sp
                    )
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CurrencyTextFieldPreview() {
    var textValue by remember { mutableFloatStateOf(97.99f) }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CurrencyTextFieldUI(
                modifier = Modifier
                    .padding(32.dp),
                value = textValue,
                label = "Desconto",
                onValueChange = {
                    textValue = it
                }
            )

            CurrencyTextFieldUI(
                modifier = Modifier
                    .padding(32.dp),
                value = textValue,
                label = "Desconto",
                isError = true,
                error = "Nome inválido",
                onValueChange = {
                    textValue = it
                }
            )
        }
    }
}