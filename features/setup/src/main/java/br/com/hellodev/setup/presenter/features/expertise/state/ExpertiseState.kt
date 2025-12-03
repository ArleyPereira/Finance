package br.com.hellodev.setup.presenter.features.expertise.state

import br.com.hellodev.domain.model.expertise.Expertise

data class ExpertiseState(
    val isLoading: Boolean = true,
    val expertises: List<Expertise> = emptyList(),
    val selectedExpertises: List<Expertise> = emptyList()
)
