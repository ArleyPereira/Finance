package br.com.hellodev.job_search.di

import br.com.hellodev.job_search.presenter.features.search.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val jobSearchModule = module {

    viewModelOf(::SearchViewModel)

}