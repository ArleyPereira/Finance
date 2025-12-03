package br.com.hellodev.main.presenter.features.main.saved.action

import br.com.hellodev.domain.model.job.item.JobItemDomain

sealed class SavedAction {
    data object OnSearch : SavedAction()
    data object OnClearSearch : SavedAction()
    data object OnRemoveJob : SavedAction()
    data class OnJobSelected(val job: JobItemDomain?) : SavedAction()
    data class OnSearchChange(val query: String) : SavedAction()
}