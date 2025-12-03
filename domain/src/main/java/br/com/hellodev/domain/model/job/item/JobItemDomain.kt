package br.com.hellodev.domain.model.job.item

import br.com.hellodev.domain.model.category.CategoryDomain
import br.com.hellodev.domain.model.company.CompanyDomain
import br.com.hellodev.domain.model.tag.TagDomain

data class JobItemDomain(
    val id: Int? = null,
    val title: String? = null,
    val address: String? = null,
    val salary: String? = null,
    val company: CompanyDomain? = null,
    val tags: List<TagDomain>? = null
) {
    companion object {
        val items = listOf(
            JobItemDomain(
                id = 1,
                title = "Desenvolvedor Android",
                address = "São Paulo, SP",
                salary = "R$ 5.000 - R$ 8.000 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            ),
            JobItemDomain(
                id = 2,
                title = "Designer UX/UI",
                address = "Remoto",
                salary = "R$ 4.000 - R$ 7.000 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            ),
            JobItemDomain(
                id = 3,
                title = "Analista de Marketing",
                address = "Belo Horizonte, MG",
                salary = "R$ 3.500 - R$ 6.000 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            ),
            JobItemDomain(
                id = 4,
                title = "Engenheiro de Software",
                address = "Porto Alegre, RS",
                salary = "R$ 6.000 - R$ 10.000 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            ),
            JobItemDomain(
                id = 5,
                title = "Analista de Dados",
                address = "Curitiba, PR",
                salary = "R$ 5.000 - R$ 9.000 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            ),
            JobItemDomain(
                id = 6,
                title = "Gerente de Projetos",
                address = "São Paulo, SP",
                salary = "R$ 8.000 - R$ 12.000 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            ),
            JobItemDomain(
                id = 7,
                title = "Desenvolvedor Front-end",
                address = "Remoto",
                salary = "R$ 4.500 - R$ 7.500 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            ),
            JobItemDomain(
                id = 8,
                title = "Estagiário em TI",
                address = "Brasília, DF",
                salary = "R$ 1.500 - R$ 2.500 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            ),
            JobItemDomain(
                id = 9,
                title = "Assistente Administrativo",
                address = "Recife, PE",
                salary = "R$ 2.000 - R$ 3.000 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            ),
            JobItemDomain(
                id = 10,
                title = "Engenheiro Civil",
                address = "Salvador, BA",
                salary = "R$ 6.000 - R$ 11.000 / mês",
                company = CompanyDomain.items.random(),
                tags = TagDomain.items.shuffled().take(2)
            )
        )

        val designJobs = mockJobsByCategory[2]

        val technologyJobs = mockJobsByCategory[3]

        val financeJobs = mockJobsByCategory[4]

        val educationJobs = mockJobsByCategory[5]

        val healthJobs = mockJobsByCategory[6]

        val marketingJobs = mockJobsByCategory[7]

        val salesJobs = mockJobsByCategory[8]

        val engineeringJobs = mockJobsByCategory[9]

        val logisticsJobs = mockJobsByCategory[10]
    }
}

val mockJobsByCategory: Map<Int, List<JobItemDomain>> = CategoryDomain.items.associate { category ->
    category.id!! to (1..10).map { index ->
        JobItemDomain(
            id = (category.id * 10) + index,
            title = "Vaga ${category.name} $index",
            address = listOf(
                "Rio Branco, AC",
                "Maceió, AL",
                "Macapá, AP",
                "Manaus, AM",
                "Salvador, BA",
                "Fortaleza, CE",
                "Brasília, DF",
                "Vitória, ES",
                "Goiânia, GO",
                "São Luís, MA",
                "Cuiabá, MT",
                "Campo Grande, MS",
                "Belo Horizonte, MG",
                "Belém, PA",
                "João Pessoa, PB",
                "Curitiba, PR",
                "Recife, PE",
                "Teresina, PI",
                "Rio de Janeiro, RJ",
                "Natal, RN",
                "Porto Alegre, RS",
                "Porto Velho, RO",
                "Boa Vista, RR",
                "Florianópolis, SC",
                "São Paulo, SP",
                "Aracaju, SE",
                "Palmas, TO"
            ).random(),
            salary = "R$ ${3000 + (index * 500)} - R$ ${5000 + (index * 700)} / mês",
            company = CompanyDomain.items.random(),
            tags = TagDomain.items.shuffled().take(2)
        )
    }
}
