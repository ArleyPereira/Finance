package br.com.hellodev.domain.model.home

import br.com.hellodev.domain.model.banner.BannerDomain
import br.com.hellodev.domain.model.job.section.JobSectionDomain
import br.com.hellodev.domain.model.user.User

data class HomeDomain(
    val profile: User? = null,
    val banners: List<BannerDomain>? = null,
    val recommendation: JobSectionDomain? = null,
    val recent: JobSectionDomain? = null
) {
    companion object {
        val homeDomainDefault = HomeDomain(
            profile = User.userDefault,
            banners = BannerDomain.items,
            recommendation = JobSectionDomain.items[0],
            recent = JobSectionDomain.items[1]
        )
    }
}
