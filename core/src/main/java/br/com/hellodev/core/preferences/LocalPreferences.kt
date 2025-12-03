package br.com.hellodev.core.preferences

import br.com.hellodev.core.constants.Preferences.ONBOARDING_VIEWED
import com.russhwolf.settings.Settings

class LocalPreferences(
    private val settings: Settings
) {

    fun saveOnboardingViewed(viewed: Boolean) {
        settings.putBoolean(ONBOARDING_VIEWED, viewed)
    }

    fun getOnboardingViewed(): Boolean {
        return settings.getBoolean(ONBOARDING_VIEWED, false)
    }

}