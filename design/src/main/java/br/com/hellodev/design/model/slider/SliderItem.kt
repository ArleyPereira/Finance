package br.com.hellodev.design.model.slider

import br.com.hellodev.design.R

data class SliderItem(
    val title: String,
    val description: String,
    val imageRes: Int
) {
    companion object {
        val getSlideItems = listOf(
            SliderItem(
                title = "Muitas Oportunidades",
                description = "Encontre vagas que combinam com suas habilidades e interesses.",
                imageRes = R.drawable.onboarding_image_1
            ),
            SliderItem(
                title = "Perfil Personalizado",
                description = "Crie um perfil profissional para atrair os melhores recrutadores.",
                imageRes = R.drawable.onboarding_image_2
            ),
            SliderItem(
                title = "Alerta de Vagas",
                description = "Receba notificações de vagas que combinam com seu perfil.",
                imageRes = R.drawable.onboarding_image_3
            )
        )
    }
}
