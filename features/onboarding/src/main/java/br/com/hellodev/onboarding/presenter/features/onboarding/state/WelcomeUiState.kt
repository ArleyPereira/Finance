package br.com.hellodev.onboarding.presenter.features.onboarding.state

import br.com.hellodev.design.model.slider.SliderItem

data class WelcomeUiState(
    val isLoading: Boolean = true,
    val slideItems: List<SliderItem> = emptyList(),
    val viewed: Boolean = false
)
