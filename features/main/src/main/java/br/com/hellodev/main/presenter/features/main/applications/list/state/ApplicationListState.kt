package br.com.hellodev.main.presenter.features.applications.list.state

import br.com.hellodev.domain.model.job.item.JobItemDomain

data class ApplicationListState(
    val isScreenLoading: Boolean = true,
    val isSearchLoading: Boolean = false,
    val query: String = "",
    val items: List<JobItemDomain>? = emptyList()
)
