pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Finance"
include(":app")
include(":design")
include(":core")
include(":di")
include(":features")
include(":features:profile")
include(":domain")
include(":data")
include(":features:job-search")
include(":features:job-details")
include(":features:onboarding")
include(":features:authentication")
include(":features:setup")
include(":features:main")
include(":features:credit_card")
