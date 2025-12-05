package br.com.hellodev.domain.model.section

import br.com.hellodev.domain.model.category.CategoryDomain
import br.com.hellodev.domain.model.job.item.JobItemDomain

data class JobSectionDomain(
    val id: Int? = null,
    val leftTitle: String? = null,
    val rightTitle: String? = null,
    val categories: List<CategoryDomain>? = null,
    val items: List<JobItemDomain>? = null
) {
    companion object {
        val items = listOf(
            JobSectionDomain(
                id = 1,
                leftTitle = "Recomendação",
                rightTitle = "Ver todas",
                items = JobItemDomain.items.shuffled().take(6)
            ),
            JobSectionDomain(
                id = 2,
                leftTitle = "Vagas em destaque",
                rightTitle = "Ver todas",
                categories = CategoryDomain.items,
                items = JobItemDomain.items
            )
        )
    }
}
