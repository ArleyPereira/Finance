package br.com.hellodev.job_details.di

import br.com.hellodev.job_details.presenter.features.applying.viewmodel.ApplyingViewModel
import br.com.hellodev.job_details.presenter.features.details.viewmodel.DetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val jobDetailsModule = module {

    viewModelOf(::DetailsViewModel)

    viewModelOf(::ApplyingViewModel)

}