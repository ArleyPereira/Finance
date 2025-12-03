package br.com.hellodev.onboarding.di

import br.com.hellodev.onboarding.presenter.features.onboarding.viewmodel.WelcomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val onboardingModule = module {

    viewModelOf(::WelcomeViewModel)

}