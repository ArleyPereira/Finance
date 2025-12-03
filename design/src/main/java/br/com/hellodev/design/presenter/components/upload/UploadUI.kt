package br.com.hellodev.design.presenter.components.upload

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.util.formatFileSize
import br.com.hellodev.core.util.getFileName
import br.com.hellodev.core.util.getFileSize
import br.com.hellodev.core.util.openPdf
import br.com.hellodev.design.presenter.components.icon.default.DefaultIcon
import br.com.hellodev.design.presenter.components.loading.CircularProgressLoading
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily
import dashedBorder

@Composable
fun UploadUI(
    modifier: Modifier = Modifier,
    label: String? = null,
    isLoading: Boolean = false,
    uri: Uri? = null,
    isError: Boolean = false,
    error: String = "",
    onLoadClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val context = LocalContext.current

    val borderColor = if (isError) {
        ColorScheme.colorScheme.alertColor
    } else {
        ColorScheme.colorScheme.upload.border
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        label?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(500),
                    color = ColorScheme.colorScheme.text.primaryColor,
                    letterSpacing = 0.2.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        uri?.let {
            Card(
                onClick = { openPdf(context, uri) },
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = ColorScheme.colorScheme.alertAlphaColor
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DefaultIcon(
                        type = IllustrationType.IC_PDF,
                        onClick = { openPdf(context, uri) }
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = getFileName(context, uri),
                            style = TextStyle(
                                lineHeight = 22.4.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(700),
                                color = ColorScheme.colorScheme.text.primaryColor,
                                letterSpacing = 0.2.sp
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = formatFileSize(getFileSize(context, uri)),
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(500),
                                color = ColorScheme.colorScheme.text.primaryColor,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }

                    DefaultIcon(
                        type = IllustrationType.IC_CLOSE,
                        onClick = onDeleteClick
                    )
                }
            }
        } ?: run {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Card(
                    onClick = onLoadClick,
                    modifier = Modifier,
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ColorScheme.colorScheme.upload.background
                    ),
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .dashedBorder(
                                    color = borderColor,
                                    shape = RoundedCornerShape(20.dp),
                                    strokeWidth = 2.dp,
                                    dashLength = 16.dp,
                                    gapLength = 16.dp
                                )
                                .padding(32.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (isLoading) {
                                CircularProgressLoading()

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Aguarde...",
                                    style = TextStyle(
                                        lineHeight = 19.6.sp,
                                        fontFamily = UrbanistFamily,
                                        fontWeight = FontWeight(600),
                                        color = ColorScheme.colorScheme.upload.text,
                                        textAlign = TextAlign.Center,
                                        letterSpacing = 0.2.sp
                                    )
                                )
                            } else {
                                DefaultIcon(
                                    type = IllustrationType.IC_UPLOAD,
                                    onClick = onLoadClick
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Escolher arquivo",
                                    style = TextStyle(
                                        lineHeight = 19.6.sp,
                                        fontFamily = UrbanistFamily,
                                        fontWeight = FontWeight(600),
                                        color = ColorScheme.colorScheme.upload.text,
                                        textAlign = TextAlign.Center,
                                        letterSpacing = 0.2.sp
                                    )
                                )
                            }
                        }
                    }
                )

                if (isError) {
                    Text(
                        text = error,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 4.dp),
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            color = ColorScheme.colorScheme.alertColor,
                            letterSpacing = 0.2.sp
                        )
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun UploadUIEmptyPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UploadUI(
                modifier = Modifier
                    .padding(24.dp),
                label = "Upload currículo",
                uri = null,
                onLoadClick = {},
                onDeleteClick = {}
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun UploadUIErrorPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UploadUI(
                modifier = Modifier
                    .padding(24.dp),
                uri = null,
                isError = true,
                error = "Escolha o arquivo",
                onLoadClick = {},
                onDeleteClick = {}
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun UploadUILoadingPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UploadUI(
                modifier = Modifier
                    .padding(24.dp),
                isLoading = true,
                onLoadClick = {},
                onDeleteClick = {}
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun UploadUIFilledPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UploadUI(
                modifier = Modifier
                    .padding(24.dp),
                uri = Uri.EMPTY,
                onLoadClick = {},
                onDeleteClick = {}
            )
        }
    }
}