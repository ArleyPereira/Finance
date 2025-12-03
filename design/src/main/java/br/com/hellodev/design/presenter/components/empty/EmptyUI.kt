package br.com.hellodev.design.presenter.components.empty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun EmptyUI(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String? = ""
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageUI(
            modifier = Modifier
                .width(250.dp),
            imageModel = R.drawable.placeholder_not_found,
            contentScale = ContentScale.FillWidth,
            previewPlaceholder = painterResource(R.drawable.placeholder_not_found),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.8.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = ColorScheme.colorScheme.text.primaryColor,
                textAlign = TextAlign.Center
            )
        )

        description?.let {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = it,
                style = TextStyle(
                    lineHeight = 25.2.sp,
                    fontFamily = UrbanistFamily,
                    color = ColorScheme.colorScheme.text.primaryColor,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.2.sp
                )
            )
        }
    }
}

@Preview
@Composable
private fun EmptyUIPreview() {
    HelloTheme(isDarkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmptyUI(
                title = "Nenhuma vaga encontrada",
                description = "Tente novamente mais tarde"
            )
        }
    }
}