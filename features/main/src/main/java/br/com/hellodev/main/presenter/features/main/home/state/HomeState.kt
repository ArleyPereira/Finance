package br.com.hellodev.main.presenter.features.main.home.state

import br.com.hellodev.domain.model.banner.BannerDomain
import br.com.hellodev.domain.model.category.CategoryDomain
import br.com.hellodev.domain.model.job.section.JobSectionDomain
import br.com.hellodev.domain.model.user.User

data class HomeState(
    val isLoading: Boolean = true,
    val query: String = "",
    val categorySelected: CategoryDomain? = CategoryDomain.items[0],
    val profile: User? = null,
    val banners: List<BannerDomain>? = null,
    val recommendation: JobSectionDomain? = null,
    val recent: JobSectionDomain? = null
)
