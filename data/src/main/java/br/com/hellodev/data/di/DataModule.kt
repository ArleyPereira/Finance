package br.com.hellodev.data.di

import br.com.hellodev.data.repository.remote.authentication.AuthenticationRepositoryImpl
import br.com.hellodev.data.repository.remote.user.UserRepositoryImpl
import br.com.hellodev.domain.repository.remote.authentication.AuthenticationRepository
import br.com.hellodev.domain.repository.remote.user.UserRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    factoryOf(::AuthenticationRepositoryImpl).bind(AuthenticationRepository::class)

    factoryOf(::UserRepositoryImpl).bind(UserRepository::class)

    // ######################### Favorite - Local #########################

}