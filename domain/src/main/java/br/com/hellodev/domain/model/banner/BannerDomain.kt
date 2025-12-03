package br.com.hellodev.domain.model.banner

data class BannerDomain(
    val id: Int? = null,
    val image: String? = null,
    val url: String? = null
) {
    companion object {
        val items = listOf(
            BannerDomain(
                id = 1,
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/banners%2Fimg_banner.webp?alt=media&token=52f94e71-f729-4861-ba9c-6e49520abcc9",
                url = "https://hellodev.com.br"
            ),
            BannerDomain(
                id = 2,
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/banners%2Fimg_banner.webp?alt=media&token=52f94e71-f729-4861-ba9c-6e49520abcc9",
                url = "https://hellodev.com.br"
            ),
            BannerDomain(
                id = 3,
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/banners%2Fimg_banner.webp?alt=media&token=52f94e71-f729-4861-ba9c-6e49520abcc9",
                url = "https://hellodev.com.br"
            ),
            BannerDomain(
                id = 4,
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/banners%2Fimg_banner.webp?alt=media&token=52f94e71-f729-4861-ba9c-6e49520abcc9",
                url = "https://hellodev.com.br"
            ),
            BannerDomain(
                id = 5,
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/banners%2Fimg_banner.webp?alt=media&token=52f94e71-f729-4861-ba9c-6e49520abcc9",
                url = "https://hellodev.com.br"
            )
        )
    }
}
