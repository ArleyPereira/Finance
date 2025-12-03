package br.com.hellodev.job_details.presenter.features.details.action

sealed class DetailsAction {
    data object Apply : DetailsAction()
}