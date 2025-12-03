package br.com.hellodev.profile.di

import org.koin.dsl.module

val profileModules = module {
    includes(
        presenterModule,
        useCaseModule,
        repositoryModule
    )
}