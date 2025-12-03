package br.com.hellodev.main.di

import br.com.hellodev.main.presenter.features.account.viewmodel.AccountViewModel
import br.com.hellodev.main.presenter.features.applications.list.viewmodel.ApplicationListViewModel
import br.com.hellodev.main.presenter.features.applications.status.viewmodel.ApplicationStatusViewModel
import br.com.hellodev.main.presenter.features.contact.viewmodel.ContactInformationViewModel
import br.com.hellodev.main.presenter.features.main.home.viewmodel.HomeViewModel
import br.com.hellodev.main.presenter.features.salary.viewmodel.SalaryExpectationViewModel
import br.com.hellodev.main.presenter.features.main.saved.viewmodel.SavedViewModel
import br.com.hellodev.main.presenter.features.summary.viewmodel.SummaryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {

    viewModelOf(::HomeViewModel)

    viewModelOf(::SavedViewModel)

    viewModelOf(::ApplicationListViewModel)

    viewModelOf(::ApplicationStatusViewModel)

    viewModelOf(::ApplicationStatusViewModel)

    viewModelOf(::AccountViewModel)

    viewModelOf(::ContactInformationViewModel)

    viewModelOf(::SummaryViewModel)

    viewModelOf(::SalaryExpectationViewModel)

}