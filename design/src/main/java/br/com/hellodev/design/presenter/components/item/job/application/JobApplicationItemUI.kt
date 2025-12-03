package br.com.hellodev.design.presenter.components.item.job.application

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.domain.model.job.item.JobItemDomain
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.tag.TagType
import br.com.hellodev.design.R
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.tag.TagUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.theme.borderStrokeDefault

@Composable
fun JobApplicationItemUI(
    modifier: Modifier = Modifier,
    job: JobItemDomain? = null,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            border = borderStrokeDefault(),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .size(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    ImageUI(
                        modifier = Modifier
                            .size(32.dp),
                        imageModel = job?.company?.image,
                        contentScale = ContentScale.Crop,
                        previewPlaceholder = painterResource(R.drawable.ic_google),
                        onClick = { }
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = job?.title ?: "",
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
                        text = job?.company?.name ?: "",
                        style = TextStyle(
                            lineHeight = 22.4.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight(500),
                            color = ColorScheme.colorScheme.text.primaryColor,
                            letterSpacing = 0.2.sp
                        )
                    )
                }

                DefaultIcon(
                    type = IllustrationType.IC_ARROW_RIGHT,
                    tint = ColorScheme.colorScheme.icon.color
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            TagUI(
                modifier = Modifier
                    .padding(start = 80.dp),
                text = "Freenlance",
                type = TagType.entries.random(),
                isAlpha = true
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun JobApplicationItemUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                .padding(24.dp)
        ) {
            JobApplicationItemUI(
                job = JobItemDomain.items.first(),
                onClick = {}
            )
        }
    }
}