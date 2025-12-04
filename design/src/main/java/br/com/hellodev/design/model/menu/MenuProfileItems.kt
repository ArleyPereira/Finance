package br.com.hellodev.design.model.menu

import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.menu.MenuType
import br.com.hellodev.design.R

sealed class MenuProfileItems(
    val illustration: IllustrationType,
    val label: Int,
    val description: Int,
    val type: MenuType
) {
    object EditProfile : MenuProfileItems(
        illustration = IllustrationType.IC_PERSON_FILL,
        label = R.string.label_edit_title_profile_screen,
        description = R.string.label_edit_message_profile_screen,
        type = MenuType.EDIT_PROFILE
    )

    object CreditCards : MenuProfileItems(
        illustration = IllustrationType.IC_CREDIT_CARD_FILL,
        label = R.string.label_credit_cards_title_profile_screen,
        description = R.string.label_credit_cards_message_profile_screen,
        type = MenuType.CREDIT_CARDS
    )
    object DarkMode : MenuProfileItems(
        illustration = IllustrationType.IC_DARK_MODE_FILL,
        label = R.string.label_theme_mode_title_profile_screen,
        description = R.string.label_theme_mode_message_profile_screen,
        type = MenuType.DARK_MODE
    )

    object Logout : MenuProfileItems(
        illustration = IllustrationType.IC_LOGOUT,
        label = R.string.label_logout_title_profile_screen,
        description = R.string.label_logout_message_profile_screen,
        type = MenuType.LOGOUT
    )

    companion object {
        fun items() = listOf(
            EditProfile,
            CreditCards,
            DarkMode,
            Logout
        )
    }
}