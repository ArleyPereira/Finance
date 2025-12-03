package br.com.hellodev.core.di

import br.com.hellodev.core.preferences.LocalPreferences
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val localModule = module {

    single { Settings() }

    single<LocalPreferences> {
        LocalPreferences(settings = get())
    }

}