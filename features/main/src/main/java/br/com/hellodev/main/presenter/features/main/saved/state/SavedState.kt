package br.com.hellodev.main.presenter.features.main.saved.state

import br.com.hellodev.domain.model.job.item.JobItemDomain

data class SavedState(
    val isScreenLoading: Boolean = true,
    val isSearchLoading: Boolean = false,
    val query: String = "",
    val items: List<JobItemDomain>? = emptyList(),
    val jobSelected: JobItemDomain? = null
)
