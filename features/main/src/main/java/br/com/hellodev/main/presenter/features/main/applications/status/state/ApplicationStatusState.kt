package br.com.hellodev.main.presenter.features.applications.status.state

import br.com.hellodev.domain.model.job.item.JobItemDomain

data class ApplicationStatusState(
    val isLoading: Boolean = true,
    val job: JobItemDomain? = null
)
