package br.com.hellodev.design.presenter.components.card.credit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.functions.format.DecimalFormat
import br.com.hellodev.design.R
import br.com.hellodev.design.extensions.modifier.shimmerPlaceholder
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.components.spacer.VerticalSpacer
import br.com.hellodev.design.presenter.components.tag.TagUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.theme.borderStrokeDefault
import br.com.hellodev.domain.model.credit_card.CreditCard

@Composable
fun CreditCardUI(
    modifier: Modifier = Modifier,
    card: CreditCard,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = ColorScheme.colorScheme.screen.backgroundSecondary
        ),
        border = borderStrokeDefault(),
        shape = RoundedCornerShape(28.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = ColorScheme.colorScheme.border.unselected
                        ),
                        shape = CircleShape,
                        content = {
                            ImageUI(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .size(42.dp)
                                    .shimmerPlaceholder(visible = isLoading),
                                imageModel = card.image,
                                shape = CircleShape,
                                previewPlaceholder = painterResource(R.drawable.nubank)
                            )
                        }
                    )

                    Text(
                        text = card.name.orEmpty(),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .shimmerPlaceholder(visible = isLoading)
                            .weight(1f),
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.text.primaryColor,
                            letterSpacing = 0.2.sp
                        )
                    )

                    TagUI(
                        text = "Visa",
                        border = BorderStroke(
                            width = 1.dp,
                            color = ColorScheme.colorScheme.tag.border
                        )
                    )
                }

                VerticalSpacer(size = 16)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Limite",
                        modifier = Modifier
                            .shimmerPlaceholder(visible = isLoading),
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.text.secondaryColor,
                            letterSpacing = 0.2.sp
                        )
                    )

                    Text(
                        text = "Fatura atual",
                        modifier = Modifier
                            .shimmerPlaceholder(visible = isLoading),
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.text.secondaryColor,
                            letterSpacing = 0.2.sp
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = DecimalFormat.format(
                            value = card.limit
                        ),
                        modifier = Modifier
                            .shimmerPlaceholder(visible = isLoading),
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight(700),
                            color = ColorScheme.colorScheme.text.primaryColor
                        )
                    )

                    Text(
                        text = DecimalFormat.format(
                            value = 1550f
                        ),
                        modifier = Modifier
                            .shimmerPlaceholder(visible = isLoading),
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight(700),
                            color = ColorScheme.colorScheme.text.primaryColor
                        )
                    )
                }

                VerticalSpacer(size = 16)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "R$ 2.000,00",
                        modifier = Modifier
                            .shimmerPlaceholder(visible = isLoading),
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.text.secondaryColor,
                            letterSpacing = 0.2.sp
                        )
                    )

                    Text(
                        text = "R$ 4.000,00",
                        modifier = Modifier
                            .shimmerPlaceholder(visible = isLoading),
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.text.secondaryColor,
                            letterSpacing = 0.2.sp
                        )
                    )
                }

                LinearProgressIndicator(
                    progress = {
                        50 / 100f
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clip(CircleShape)
                        .height(8.dp)
                        .shimmerPlaceholder(visible = isLoading),
                    color = ColorScheme.colorScheme.successColor,
                    trackColor = ColorScheme.colorScheme.successColor.copy(alpha = 0.2f),
                    strokeCap = StrokeCap.Butt,
                    gapSize = 0.dp,
                    drawStopIndicator = {}
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Fechamento: ${card.closingDay}",
                        modifier = Modifier
                            .shimmerPlaceholder(visible = isLoading),
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.text.secondaryColor,
                            letterSpacing = 0.2.sp
                        )
                    )

                    Text(
                        text = "Vencimento: ${card.dueDate}",
                        modifier = Modifier
                            .shimmerPlaceholder(visible = isLoading),
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.text.secondaryColor,
                            letterSpacing = 0.2.sp
                        )
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun CreditCardUIPreview() {
    HelloTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(CreditCard.items) { item ->
                CreditCardUI(
                    card = item,
                    onClick = {}
                )
            }
        }
    }
}