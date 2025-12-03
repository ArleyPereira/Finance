package br.com.hellodev.core.di

import org.koin.dsl.module

val coreModules = module {
    includes(
        localModule
    )
}