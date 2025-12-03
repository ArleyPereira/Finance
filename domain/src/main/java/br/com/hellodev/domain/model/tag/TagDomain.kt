package br.com.hellodev.domain.model.tag

data class TagDomain(
    val id: Int? = null,
    val name: String? = null
) {
    companion object {
        val items = listOf(
            TagDomain(
                id = 1,
                name = "Full Time"
            ),
            TagDomain(
                id = 2,
                name = "Presencial"
            ),
            TagDomain(
                id = 3,
                name = "Remoto"
            ),
            TagDomain(
                id = 4,
                name = "Freelance"
            ),
            TagDomain(
                id = 5,
                name = "Estágio"
            ),
            TagDomain(
                id = 6,
                name = "Júnior"
            ),
            TagDomain(
                id = 7,
                name = "Pleno"
            ),
            TagDomain(
                id = 8,
                name = "Sênior"
            ),
            TagDomain(
                id = 9,
                name = "Híbrido"
            )
        )
    }
}
