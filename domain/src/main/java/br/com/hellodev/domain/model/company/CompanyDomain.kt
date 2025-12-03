package br.com.hellodev.domain.model.company

data class CompanyDomain(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null
) {
    companion object {
        val items = listOf(
            CompanyDomain(
                id = 1,
                name = "Airbnb",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_airbnb.png?alt=media&token=91701c8d-ae4a-4610-b791-655f2e550e90"
            ),
            CompanyDomain(
                id = 2,
                name = "Apple",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_apple.png?alt=media&token=09d3f972-871f-43b5-9e4c-01795403db5a"
            ),
            CompanyDomain(
                id = 3,
                name = "Dribbble",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_dribbble.png?alt=media&token=921e9abe-91a7-468c-9103-563036be8f2d"
            ),
            CompanyDomain(
                id = 4,
                name = "Figma",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_figma.png?alt=media&token=65e888f1-37df-4406-a295-a29524bd795c"
            ),
            CompanyDomain(
                id = 5,
                name = "Google",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_google.png?alt=media&token=5db69251-b95b-45ef-85e6-c108c80ee41a"
            ),
            CompanyDomain(
                id = 6,
                name = "PayPal",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_paypal.png?alt=media&token=781b0c7a-c20c-4a4d-887a-9132a157b30a"
            ),
            CompanyDomain(
                id = 7,
                name = "Pinterest",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_pinterest.png?alt=media&token=88e4e60c-09db-4d6f-ad09-36f55f1d9e6e"
            ),
            CompanyDomain(
                id = 8,
                name = "Reddit",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_reddit.png?alt=media&token=831ffabd-4869-4add-b6d5-a4ec0a657a3e"
            ),
            CompanyDomain(
                id = 9,
                name = "Sketch",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_sketch.png?alt=media&token=92b5d952-2db6-433f-adec-60b840c4003e"
            ),
            CompanyDomain(
                id = 10,
                name = "Snapchat",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_snapchat.png?alt=media&token=788c456d-25f1-4874-a8c8-29637dcd3b17"
            ),
            CompanyDomain(
                id = 11,
                name = "Spotify",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_spotify.png?alt=media&token=da637c4b-8256-4b39-857f-6b6466e76d10"
            ),
            CompanyDomain(
                id = 12,
                name = "Twitter",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_twitter.png?alt=media&token=1dcca377-a829-482d-9cdd-55af389dd45f"
            ),
            CompanyDomain(
                id = 13,
                name = "WeChat",
                image = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/company%2Fimg_wechat.png?alt=media&token=c0f24996-2be2-402b-9e78-0d67823fb630"
            ),
        )
    }
}
