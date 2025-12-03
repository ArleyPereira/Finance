package br.com.hellodev.design.presenter.components.bar.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_FILTER
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun SearchBarUI(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    maxLength: Int = Int.MAX_VALUE,
    trailingIcon: @Composable (() -> Unit)? = null,
    onBackPressed: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearchAction: () -> Unit
) {
    val controller = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = ColorScheme.colorScheme.defaultColor,
        backgroundColor = ColorScheme.colorScheme.alphaDefaultColor
    )

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBackPressed != null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onBackPressed
                        ),
                    tint = ColorScheme.colorScheme.icon.color
                )

                Spacer(modifier = Modifier.width(16.dp))
            }

            TextField(
                value = value,
                onValueChange = { value ->
                    if (value.length <= maxLength) {
                        onValueChange(value)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.textField.placeholder,
                            letterSpacing = 0.2.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                trailingIcon = trailingIcon,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        controller?.hide()
                        onSearchAction()
                    }
                ),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = ColorScheme.colorScheme.textField.background,
                    focusedContainerColor = ColorScheme.colorScheme.textField.background,
                    disabledContainerColor = ColorScheme.colorScheme.textField.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorContainerColor = ColorScheme.colorScheme.alertAlphaColor,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedTextColor = ColorScheme.colorScheme.textField.text,
                    focusedTextColor = ColorScheme.colorScheme.textField.text,
                    errorTextColor = ColorScheme.colorScheme.textField.text,
                    disabledTextColor = ColorScheme.colorScheme.textField.disabledText,
                    cursorColor = ColorScheme.colorScheme.defaultColor
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SearchBarUIPreview() {
    var textValue by remember {
        mutableStateOf("")
    }

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBarUI(
                modifier = Modifier
                    .padding(24.dp),
                value = textValue,
                placeholder = "Pesquise pela vaga ou empresa",
                trailingIcon = {
                    IconButton(
                        onClick = {},
                        content = {
                            IconButton(
                                onClick = {},
                                content = {
                                    DefaultIcon(type = IC_FILTER)
                                }
                            )
                        }
                    )
                },
                onValueChange = {
                    textValue = it
                },
                onSearchAction = {

                }
            )

            SearchBarUI(
                modifier = Modifier
                    .padding(24.dp),
                value = textValue,
                placeholder = "Pesquise pela vaga ou empresa",
                onBackPressed = {},
                onValueChange = {
                    textValue = it
                },
                onSearchAction = {

                }
            )
        }
    }
}