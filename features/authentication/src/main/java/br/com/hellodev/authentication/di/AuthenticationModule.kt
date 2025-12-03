package br.com.hellodev.authentication.di

import br.com.hellodev.authentication.presenter.features.login.viewmodel.LoginViewModel
import br.com.hellodev.authentication.presenter.features.signup.viewmodel.SignupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authenticationModule = module {

    viewModelOf(::LoginViewModel)

    viewModelOf(::SignupViewModel)

}