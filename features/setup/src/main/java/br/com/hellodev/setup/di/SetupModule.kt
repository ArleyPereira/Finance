package br.com.hellodev.setup.di

import br.com.hellodev.setup.presenter.features.country.viewmodel.CountryViewModel
import br.com.hellodev.setup.presenter.features.expertise.viewmodel.ExpertiseViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val setupModule = module {

    viewModelOf(::CountryViewModel)

    viewModelOf(::ExpertiseViewModel)

}