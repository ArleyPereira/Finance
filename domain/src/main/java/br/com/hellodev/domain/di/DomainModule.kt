package br.com.hellodev.domain.di

import br.com.hellodev.domain.usecase.remote.authentication.LoginUseCase
import br.com.hellodev.domain.usecase.remote.authentication.RegisterUseCase
import br.com.hellodev.domain.usecase.remote.user.GetUserUseCase
import br.com.hellodev.domain.usecase.remote.user.SaveImageUserUseCase
import br.com.hellodev.domain.usecase.remote.user.SaveUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    // ######################### Authentication #########################

    factoryOf(::RegisterUseCase)

    factoryOf(::LoginUseCase)

    // ######################### User #########################

    factoryOf(::SaveUserUseCase)

    factoryOf(::GetUserUseCase)

    factoryOf(::SaveImageUserUseCase)
}