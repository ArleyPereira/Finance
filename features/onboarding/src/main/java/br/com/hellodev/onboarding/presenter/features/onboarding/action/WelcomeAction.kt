package br.com.hellodev.onboarding.presenter.features.onboarding.action

sealed class WelcomeAction {
    data object OnboardingViewed : WelcomeAction()
}