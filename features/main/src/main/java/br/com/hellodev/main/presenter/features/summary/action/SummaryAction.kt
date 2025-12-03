package br.com.hellodev.main.presenter.features.summary.action

sealed class SummaryAction {
    data object Save : SummaryAction()
}