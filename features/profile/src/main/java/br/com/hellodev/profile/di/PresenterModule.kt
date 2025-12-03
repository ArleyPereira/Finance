package br.com.hellodev.profile.di

import br.com.hellodev.profile.presenter.features.profile.viewmodel.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presenterModule = module {
    viewModelOf(::ProfileViewModel)
}