package br.com.hellodev.design.presenter.components.card.account

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.account.AccountCardActionType
import br.com.hellodev.core.enums.account.AccountCardActionType.ADD
import br.com.hellodev.core.enums.account.AccountCardActionType.EDIT
import br.com.hellodev.core.enums.account.AccountCardType
import br.com.hellodev.core.enums.account.AccountCardType.CONTACT_INFORMATION
import br.com.hellodev.core.enums.account.AccountCardType.CV_RESUME
import br.com.hellodev.core.enums.account.AccountCardType.EDUCATION
import br.com.hellodev.core.enums.account.AccountCardType.EXPECTED_SALARY
import br.com.hellodev.core.enums.account.AccountCardType.LANGUAGES
import br.com.hellodev.core.enums.account.AccountCardType.PROJECTS
import br.com.hellodev.core.enums.account.AccountCardType.SKILLS
import br.com.hellodev.core.enums.account.AccountCardType.SUMMARY
import br.com.hellodev.core.enums.account.AccountCardType.WORK_EXPERIENCE
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ADD
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EDIT_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EMAIL_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_LOCATION_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_PERSON_FILL
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_PHONE_LINE
import br.com.hellodev.design.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.icon.default.getDrawableIllustration
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import br.com.hellodev.design.presenter.theme.borderStrokeDefault

@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    title: String,
    cardType: AccountCardType,
    actionType: AccountCardActionType?,
    content: @Composable () -> Unit,
    onActionClick: () -> Unit
) {
    var expandedState by remember { mutableStateOf(false) }

    val leftIllustrationType = when (cardType) {
        CONTACT_INFORMATION -> IC_PERSON_FILL
        SUMMARY -> IllustrationType.IC_SUMMARY
        EXPECTED_SALARY -> IllustrationType.IC_EXPECTED_SALARY
        WORK_EXPERIENCE -> IllustrationType.IC_WORK_EXPERIENCE
        EDUCATION -> IllustrationType.IC_EDUCATION
        PROJECTS -> IllustrationType.IC_PROJECTS
        LANGUAGES -> IllustrationType.IC_LANGUAGES
        SKILLS -> IllustrationType.IC_SKILLS
        CV_RESUME -> IllustrationType.IC_CV_RESUME
    }

    val rightIconType = when (actionType) {
        EDIT -> IC_EDIT_LINE
        ADD -> IC_ADD
        else -> null
    }

    Card(
        onClick = { expandedState = !expandedState },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorScheme.colorScheme.screen.backgroundSecondary
        ),
        border = borderStrokeDefault(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    DefaultIcon(
                        type = leftIllustrationType,
                        onClick = { expandedState = !expandedState }
                    )

                    Text(
                        text = title,
                        modifier = Modifier
                            .weight(1f),
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight(700),
                            color = ColorScheme.colorScheme.text.primaryColor
                        )
                    )

                    rightIconType?.let { type ->
                        DefaultIcon(
                            type = type,
                            onClick = onActionClick
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 400,
                                easing = LinearOutSlowInEasing
                            )
                        )
                ) {
                    if (expandedState) {
                        HorizontalDividerUI(
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                        )

                        content()
                    }
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun AccountCardPreview() {
    HelloTheme {
        AccountCard(
            modifier = Modifier
                .padding(24.dp),
            title = "Informações de contato",
            cardType = CONTACT_INFORMATION,
            actionType = ADD,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            painter = getDrawableIllustration(type = IC_LOCATION_LINE),
                            contentDescription = null,
                            tint = ColorScheme.colorScheme.icon.color
                        )

                        Text(
                            text = "Vitória, Espiríto Santo",
                            style = TextStyle(
                                lineHeight = 22.4.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(500),
                                color = ColorScheme.colorScheme.text.primaryColor,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            painter = getDrawableIllustration(type = IC_PHONE_LINE),
                            contentDescription = null,
                            tint = ColorScheme.colorScheme.icon.color
                        )

                        Text(
                            text = "(27) 99637-5733",
                            style = TextStyle(
                                lineHeight = 22.4.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(500),
                                color = ColorScheme.colorScheme.text.primaryColor,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            painter = getDrawableIllustration(type = IC_EMAIL_LINE),
                            contentDescription = null,
                            tint = ColorScheme.colorScheme.icon.color
                        )

                        Text(
                            text = "arley.santana@hellodev.com.br",
                            style = TextStyle(
                                lineHeight = 22.4.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(500),
                                color = ColorScheme.colorScheme.text.primaryColor,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }
                }
            },
            onActionClick = {}
        )
    }
}