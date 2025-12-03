package br.com.hellodev.di

import br.com.hellodev.authentication.di.authenticationModule
import br.com.hellodev.core.di.coreModules
import br.com.hellodev.data.di.dataModule
import br.com.hellodev.domain.di.domainModule
import br.com.hellodev.job_details.di.jobDetailsModule
import br.com.hellodev.job_search.di.jobSearchModule
import br.com.hellodev.main.di.mainModule
import br.com.hellodev.onboarding.di.onboardingModule
import br.com.hellodev.profile.di.profileModules
import br.com.hellodev.setup.di.setupModule
import org.koin.dsl.module

val appModules = module {
    includes(
        coreModules,
        domainModule,
        dataModule,
        onboardingModule,
        authenticationModule,
        setupModule,
        mainModule,
        jobSearchModule,
        jobDetailsModule,
        profileModules
    )
}