package br.com.hellodev.domain.model.expertise

data class Expertise(
    val id: Int? = null,
    val name: String? = null
) {
    companion object {
        fun items() = listOf(
            Expertise(
                id = 1,
                name = "Contabilidade e Finanças"
            ),
            Expertise(
                id = 2,
                name = "Arquitetura e Engenharia"
            ),
            Expertise(
                id = 3,
                name = "Gestão e Consultoria"
            ),
            Expertise(
                id = 4,
                name = "Mídia, design e criativos"
            ),
            Expertise(
                id = 5,
                name = "Vendas e Marketing"
            ),
            Expertise(
                id = 6,
                name = "Escrita e Conteúdo"
            ),
            Expertise(
                id = 7,
                name = "Educação e Aprendizagem"
            ),
            Expertise(
                id = 8,
                name = "Saúde e Bem-estar"
            ),
            Expertise(
                id = 9,
                name = "Segurança da Informação"
            ),
            Expertise(
                id = 10,
                name = "Tecnologia da Informação"
            )
        )
    }
}
