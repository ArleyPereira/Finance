package br.com.hellodev.domain.model.user

data class User(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val avatar: String? = null
) {
    companion object Companion {
        val userDefault = User(
            id = "1AS8DAS-DASD-ASDASD-ASDASD",
            firstName = "Arley",
            lastName = "Santana",
            email = "arley.santana@gmail.com",
            phone = "27996375733",
            avatar = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/user%2Fphoto.jpg?alt=media&token=be293f25-430d-4e58-8d90-4231f0faa39a"
        )
    }
}
