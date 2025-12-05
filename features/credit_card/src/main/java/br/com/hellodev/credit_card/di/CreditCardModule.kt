package br.com.hellodev.credit_card.di

import br.com.hellodev.credit_card.presenter.features.form.viewmodel.FormCreditCardViewModel
import br.com.hellodev.credit_card.presenter.features.list.viewmodel.ListCreditCardViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val creditCardModule = module {

    viewModelOf(::ListCreditCardViewModel)

    viewModelOf(::FormCreditCardViewModel)

}