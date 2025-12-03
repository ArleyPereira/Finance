package br.com.hellodev.design.presenter.components.item.job.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.tag.TagUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.theme.borderStrokeDefault

@Composable
fun JobDetailsItemUI(
    modifier: Modifier = Modifier,
    job: JobItemDomain
) {
    Card(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorScheme.colorScheme.screen.backgroundSecondary
        ),
        border = borderStrokeDefault(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            border = borderStrokeDefault(),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .size(92.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ImageUI(
                        modifier = Modifier
                            .size(60.dp),
                        imageModel = job.company?.image,
                        contentScale = ContentScale.Crop,
                        previewPlaceholder = painterResource(R.drawable.ic_google),
                        enabled = false,
                        onClick = {}
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = job.title ?: "",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(700),
                        color = ColorScheme.colorScheme.text.primaryColor
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = job.company?.name ?: "",
                    style = TextStyle(
                        lineHeight = 22.4.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(500),
                        color = ColorScheme.colorScheme.defaultColor,
                        letterSpacing = 0.2.sp
                    )
                )

                HorizontalDividerUI(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )

                Text(
                    text = job.address ?: "",
                    style = TextStyle(
                        lineHeight = 25.2.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(500),
                        color = ColorScheme.colorScheme.text.primaryColor,
                        letterSpacing = 0.2.sp
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = job.salary ?: "",
                    style = TextStyle(
                        lineHeight = 25.2.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(600),
                        color = ColorScheme.colorScheme.defaultColor,
                        letterSpacing = 0.2.sp
                    )
                )

                Row(
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    job.tags?.forEach { tag ->
                        TagUI(
                            text = tag.name ?: "",
                            border = BorderStroke(
                                width = 1.dp,
                                color = ColorScheme.colorScheme.tag.border
                            )
                        )
                    }
                }

                Text(
                    text = "Publicado há 10 dias, termina em 31 de dezembro.",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(500),
                        color = ColorScheme.colorScheme.text.primaryColor,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp
                    )
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun JobDetailsItemUIPreview() {
    HelloTheme {
        JobDetailsItemUI(
            job = JobItemDomain.items.first()
        )
    }
}