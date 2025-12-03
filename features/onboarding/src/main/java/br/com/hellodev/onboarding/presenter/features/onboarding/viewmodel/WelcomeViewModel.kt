package br.com.hellodev.onboarding.presenter.features.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.core.preferences.LocalPreferences
import br.com.hellodev.design.model.slider.SliderItem
import br.com.hellodev.onboarding.presenter.features.onboarding.action.WelcomeAction
import br.com.hellodev.onboarding.presenter.features.onboarding.state.WelcomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WelcomeViewModel(
    private val localPreferences: LocalPreferences
) : ViewModel() {

    private var _state = MutableStateFlow(WelcomeUiState())
    var state: StateFlow<WelcomeUiState> = _state

    init {
        initData()
    }

    fun dispatchAction(action: WelcomeAction) {
        when (action) {
            is WelcomeAction.OnboardingViewed -> {
                saveOnboardingViewed()
            }
        }
    }

    private fun initData() {
        val onboardingViewed = localPreferences.getOnboardingViewed()

        _state.update {
            it.copy(
                slideItems = SliderItem.getSlideItems,
                isLoading = false,
                viewed = onboardingViewed
            )
        }
    }

    private fun saveOnboardingViewed() {
        localPreferences.saveOnboardingViewed(true)

        _state.update {
            it.copy(viewed = true)
        }
    }

}