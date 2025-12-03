package br.com.hellodev.job_search.presenter.features.search.state

import br.com.hellodev.domain.model.job.item.JobItemDomain

data class SearchState(
    val isLoading: Boolean = true,
    val query: String = "",
    val jobs: List<JobItemDomain> = emptyList()
)
