package br.com.hellodev.domain.model.category

data class CategoryDomain(
    val id: Int? = null,
    val name: String? = null
) {
    companion object {
        val items = listOf(
            CategoryDomain(
                id = 1,
                name = "Todos"
            ),
            CategoryDomain(
                id = 2,
                name = "Design"
            ),
            CategoryDomain(
                id = 3,
                name = "Tecnologia"
            ),
            CategoryDomain(
                id = 4,
                name = "Finanças"
            ),
            CategoryDomain(
                id = 5,
                name = "Educação"
            ),
            CategoryDomain(
                id = 6,
                name = "Saúde"
            ),
            CategoryDomain(
                id = 7,
                name = "Marketing"
            ),
            CategoryDomain(
                id = 8,
                name = "Vendas"
            ),
            CategoryDomain(
                id = 9,
                name = "Engenharia"
            ),
            CategoryDomain(
                id = 10,
                name = "Logística"
            )
        )
    }
}

