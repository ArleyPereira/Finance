package br.com.hellodev.profile.di

import br.com.hellodev.profile.presenter.features.edit_profile.viewmodel.EditProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presenterModule = module {
    viewModelOf(::EditProfileViewModel)
}