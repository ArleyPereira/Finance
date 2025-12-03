package br.com.hellodev.job_details.presenter.features.details.state

import br.com.hellodev.domain.model.job.item.JobItemDomain

data class DetailsState(
    val isLoading: Boolean = true,
    val job: JobItemDomain? = null,
)
