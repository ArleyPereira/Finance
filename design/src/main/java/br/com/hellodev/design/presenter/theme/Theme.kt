package br.com.hellodev.design.presenter.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import br.com.hellodev.design.presenter.theme.scheme.BorderColorScheme
import br.com.hellodev.design.presenter.theme.scheme.ButtonColorScheme
import br.com.hellodev.design.presenter.theme.scheme.CheckColorScheme
import br.com.hellodev.design.presenter.theme.scheme.DividerColorScheme
import br.com.hellodev.design.presenter.theme.scheme.IconColorScheme
import br.com.hellodev.design.presenter.theme.scheme.MenuItemScheme
import br.com.hellodev.design.presenter.theme.scheme.MyColorScheme
import br.com.hellodev.design.presenter.theme.scheme.RadioColorScheme
import br.com.hellodev.design.presenter.theme.scheme.ScreenColorScheme
import br.com.hellodev.design.presenter.theme.scheme.ShimmerScheme
import br.com.hellodev.design.presenter.theme.scheme.SocialButtonColorScheme
import br.com.hellodev.design.presenter.theme.scheme.SwitchColorScheme
import br.com.hellodev.design.presenter.theme.scheme.TagColorScheme
import br.com.hellodev.design.presenter.theme.scheme.TextColorScheme
import br.com.hellodev.design.presenter.theme.scheme.TextFieldColorScheme
import br.com.hellodev.design.presenter.theme.scheme.TopAppBarColorScheme
import br.com.hellodev.design.presenter.theme.scheme.UploadColorScheme

private val LightColorScheme = MyColorScheme(
    text = TextColorScheme(
        primaryColor = PrimaryTextColorLight,
        secondaryColor = SecondaryTextColorLight,
        disabled = DisabledTextColorLight
    ),
    screen = ScreenColorScheme(
        backgroundPrimary = PrimaryBackgroundColorLight,
        backgroundSecondary = SecondaryBackgroundColorLight
    ),
    icon = IconColorScheme(
        color = IconColorLight,
        default = IconDefaultColor
    ),
    button = ButtonColorScheme(
        primaryBackground = PrimaryButtonColor,
        secondaryBackground = SecondaryButtonColorLight,
        primaryText = PrimaryButtonTextColorLight,
        secondaryText = SecondaryButtonTextColorLight
    ),
    border = BorderColorScheme(
        selected = SelectedBorderColor,
        unselected = UnselectedBorderColorLight
    ),
    divider = DividerColorScheme(
        color = DividerColorLight
    ),
    socialButton = SocialButtonColorScheme(
        background = BackgroundSocialButtonColorLight,
        border = BorderSocialButtonColorLight,
        text = TextSocialButtonColorLight
    ),
    textField = TextFieldColorScheme(
        background = TextFieldBackgroundColorLight,
        text = TextFieldTextColorLight,
        placeholder = TextFieldPlaceholderColor,
        disabledText = DisabledTextFieldTextColorLight
    ),
    menuItem = MenuItemScheme(
        background = MenuItemBackgroundColorLight
    ),
    switch = SwitchColorScheme(
        selectedBackground = SwitchSelectedBackgroundColor,
        unselectedBackground = SwitchUnselectedBackgroundColor
    ),
    topAppBar = TopAppBarColorScheme(
        background = TopAppBarColorLight,
        content = TopAppBarContentColorLight
    ),
    radio = RadioColorScheme(
        selectedColor = RadioSelectedColor,
        unselectedColor = RadioUnselectedColor
    ),
    check = CheckColorScheme(
        checked = CheckedColor,
        unchecked = UncheckedColor,
    ),
    tag = TagColorScheme(
        background = TagBackgroundColorLight,
        text = TagTextColorLight,
        border = UnselectedBorderColorLight
    ),
    upload = UploadColorScheme(
        background = UploadBackgroundLight,
        border = UploadBorderColorLight,
        text = UploadTextColorLight
    ),
    shimmer = ShimmerScheme(
        background = BackgroundShimmerColorLight,
        highlight = HighlightShimmerColorLight
    ),
    defaultColor = DefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    alphaDefaultColor = AlphaDefaultColor,
    successColor = SuccessColor,
    alertColor = AlertColor,
    alertAlphaColor = AlertAlphaColor,
    warningColor = WarningColor,
    infoColor = InfoColor,
    disabledColor = DisabledColor,
    greyscale900Color = Greyscale900Color,
    greyscale800Color = Greyscale800Color,
    greyscale700Color = Greyscale700Color,
    greyscale600Color = Greyscale600Color,
    greyscale500Color = Greyscale500Color,
    greyscale400Color = Greyscale400Color,
    greyscale300Color = Greyscale300Color,
    greyscale200Color = Greyscale200Color,
    greyscale100Color = Greyscale100Color,
    greyscale50Color = Greyscale50Color,
    spotColor = SpotColor,
    ambientColor = AmbientColor
)

private val DarkColorScheme = MyColorScheme(
    text = TextColorScheme(
        primaryColor = PrimaryTextColorDark,
        secondaryColor = SecondaryTextColorDark,
        disabled = DisabledTextColorDark
    ),
    screen = ScreenColorScheme(
        backgroundPrimary = PrimaryBackgroundColorDark,
        backgroundSecondary = SecondaryBackgroundColorDark
    ),
    icon = IconColorScheme(
        color = IconColorDark,
        default = IconDefaultColor
    ),
    button = ButtonColorScheme(
        primaryBackground = PrimaryButtonColor,
        secondaryBackground = SecondaryButtonColorDark,
        primaryText = PrimaryButtonTextColorDark,
        secondaryText = SecondaryButtonTextColorDark
    ),
    border = BorderColorScheme(
        selected = SelectedBorderColor,
        unselected = UnselectedBorderColorDark
    ),
    divider = DividerColorScheme(
        color = DividerColorDark
    ),
    socialButton = SocialButtonColorScheme(
        background = BackgroundSocialButtonColorDark,
        border = BorderSocialButtonColorDark,
        text = TextSocialButtonColorDark
    ),
    textField = TextFieldColorScheme(
        background = TextFieldBackgroundColorDark,
        text = TextFieldTextColorDark,
        placeholder = TextFieldPlaceholderColor,
        disabledText = DisabledTextFieldTextColorDark
    ),
    menuItem = MenuItemScheme(
        background = MenuItemBackgroundColorDark
    ),
    switch = SwitchColorScheme(
        selectedBackground = SwitchSelectedBackgroundColor,
        unselectedBackground = SwitchUnselectedBackgroundColor
    ),
    topAppBar = TopAppBarColorScheme(
        background = TopAppBarColorDark,
        content = TopAppBarContentColorDark
    ),
    radio = RadioColorScheme(
        selectedColor = RadioSelectedColor,
        unselectedColor = RadioUnselectedColor
    ),
    check = CheckColorScheme(
        checked = CheckedColor,
        unchecked = UncheckedColor,
    ),
    tag = TagColorScheme(
        background = TagBackgroundColorDark,
        text = TagTextColorDark,
        border = UnselectedBorderColorDark
    ),
    upload = UploadColorScheme(
        background = UploadBackgroundColorDark,
        border = UploadBorderColorDark,
        text = UploadTextColorDark
    ),
    shimmer = ShimmerScheme(
        background = BackgroundShimmerColorDark,
        highlight = HighlightShimmerColorDark
    ),
    defaultColor = DefaultColor,
    disabledDefaultColor = DisabledDefaultColor,
    alphaDefaultColor = AlphaDefaultColor,
    successColor = SuccessColor,
    alertColor = AlertColor,
    alertAlphaColor = AlertAlphaColor,
    warningColor = WarningColor,
    infoColor = InfoColor,
    disabledColor = DisabledColor,
    greyscale900Color = Greyscale900Color,
    greyscale800Color = Greyscale800Color,
    greyscale700Color = Greyscale700Color,
    greyscale600Color = Greyscale600Color,
    greyscale500Color = Greyscale500Color,
    greyscale400Color = Greyscale400Color,
    greyscale300Color = Greyscale300Color,
    greyscale200Color = Greyscale200Color,
    greyscale100Color = Greyscale100Color,
    greyscale50Color = Greyscale50Color,
    spotColor = SpotColor,
    ambientColor = AmbientColor
)

private val LocalColorScheme = compositionLocalOf { LightColorScheme }

object ColorScheme {
    val colorScheme: MyColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
}

@Composable
fun HelloTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme by remember(isDarkTheme) {
        mutableStateOf(if (isDarkTheme) DarkColorScheme else LightColorScheme)
    }

    CompositionLocalProvider(LocalColorScheme provides colorScheme) {
        MaterialTheme(content = content)
    }
}