package br.com.hellodev.design.presenter.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_FACEBOOK
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_GITHUB
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_GOOGLE
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SocialButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    illustrationType: IllustrationType,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("button_loading.json"))

    Button(
        onClick = onClick,
        modifier = modifier
            .height(58.dp)
            .border(
                width = 1.dp,
                color = ColorScheme.colorScheme.socialButton.border,
                shape = RoundedCornerShape(16.dp)
            ),
        enabled = !isLoading,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorScheme.colorScheme.socialButton.background,
        ),
        content = {
            if (isLoading) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier
                        .size(70.dp),
                    iterations = LottieConstants.IterateForever,
                    maintainOriginalImageBounds = true,
                    contentScale = ContentScale.Crop
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    DefaultIcon(
                        type = illustrationType,
                        contentDescription = text
                    )

                    text?.let {
                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = text,
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 22.4.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight.Bold,
                                color = ColorScheme.colorScheme.socialButton.text,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }
                }
            }

        }
    )
}

@PreviewLightDark
@Composable
private fun SocialButtonPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            SocialButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Continuar com o Google",
                illustrationType = IC_GOOGLE,
                isLoading = false,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            SocialButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Continuar com o Facebook",
                illustrationType = IC_FACEBOOK,
                isLoading = false,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            SocialButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Continuar com o Github",
                illustrationType = IC_GITHUB,
                isLoading = false,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SocialButton(
                    illustrationType = IC_GOOGLE,
                    isLoading = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.width(20.dp))

                SocialButton(
                    illustrationType = IC_FACEBOOK,
                    isLoading = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.width(20.dp))

                SocialButton(
                    illustrationType = IC_GITHUB,
                    isLoading = false,
                    onClick = {}
                )
            }
        }
    }
}