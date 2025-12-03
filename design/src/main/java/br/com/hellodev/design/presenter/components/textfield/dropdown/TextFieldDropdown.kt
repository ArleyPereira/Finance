package br.com.hellodev.design.presenter.components.textfield.dropdown

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.getDrawableIllustration
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDropdown(
    modifier: Modifier = Modifier,
    items: List<String>,
    label: String = "",
    illustrationType: IllustrationType,
    isError: Boolean = false,
    enabled: Boolean = true,
    error: String = "",
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val textFieldState = rememberTextFieldState(items[0])
    val rotation by animateFloatAsState(
        targetValue = if (expanded) {
            180f
        } else {
            0f
        },
        animationSpec = tween(durationMillis = 500), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.4.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(500),
                color = ColorScheme.colorScheme.text.primaryColor,
                letterSpacing = 0.2.sp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (isError) {
                            ColorScheme.colorScheme.alertColor
                        } else {
                            Color.Transparent
                        },
                        shape = RoundedCornerShape(16.dp)
                    )
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                state = textFieldState,
                readOnly = true,
                lineLimits = TextFieldLineLimits.SingleLine,
                trailingIcon = {
                    Icon(
                        painter = getDrawableIllustration(IllustrationType.IC_ARROW_DOWN),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(rotation),
                        tint = ColorScheme.colorScheme.icon.color
                    )
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(600),
                    color = ColorScheme.colorScheme.text.primaryColor,
                    letterSpacing = 0.2.sp
                ),
                shape = if (expanded) {
                    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                } else {
                    RoundedCornerShape(16.dp)
                },
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

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                shape = if (expanded) {
                    RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                } else {
                    RoundedCornerShape(0.dp)
                },
                containerColor = ColorScheme.colorScheme.textField.background,
                shadowElevation = 0.dp,
            ) {
                HorizontalDividerUI()

                items.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 22.4.sp,
                                    fontFamily = UrbanistFamily,
                                    fontWeight = FontWeight(600),
                                    color = ColorScheme.colorScheme.text.primaryColor,
                                    letterSpacing = 0.2.sp
                                ),
                            )
                        },
                        onClick = {
                            textFieldState.setTextAndPlaceCursorAtEnd(option)
                            onOptionSelected(option)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

    }
}

@PreviewLightDark
@Composable
fun TextFieldDropdownPreview() {
    val item: List<String> = listOf(
        "Mensal",
        "Semestral",
        "Anual"
    )

    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldDropdown(
                modifier = Modifier
                    .padding(24.dp),
                items = item,
                label = "teste",
                illustrationType = IllustrationType.IC_RIGHT,
                onOptionSelected = {}
            )
        }
    }
}