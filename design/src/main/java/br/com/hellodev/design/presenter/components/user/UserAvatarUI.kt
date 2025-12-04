package br.com.hellodev.design.presenter.components.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.design.R
import br.com.hellodev.design.extensions.modifier.shimmerPlaceholder
import br.com.hellodev.design.presenter.components.image.ImageFromInitials
import br.com.hellodev.design.presenter.components.image.ImageUI
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.HelloTheme

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    avatar: String?,
    firstName: String?,
    lastName: String?,
    isLoading: Boolean
) {
    if (avatar?.isNotEmpty() == true) {
        ImageUI(
            modifier = modifier
                .shimmerPlaceholder(visible = isLoading, shape = CircleShape),
            imageModel = avatar,
            contentScale = ContentScale.Crop,
            previewPlaceholder = painterResource(R.drawable.placeholder_photo_profile),
            shape = CircleShape,
            isLoading = false,
            onClick = {}
        )
    } else {
        ImageFromInitials(
            modifier = modifier
                .shimmerPlaceholder(visible = isLoading, shape = CircleShape),
            firstName = firstName,
            lastName = lastName,
            onClick = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun UserAvatarPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .background(ColorScheme.colorScheme.screen.backgroundPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserAvatar(
                modifier = Modifier
                    .size(32.dp),
                avatar = "",
                firstName = "John",
                lastName = "Doe",
                isLoading = false
            )

            UserAvatar(
                modifier = Modifier
                    .size(32.dp),
                avatar = null,
                firstName = "John",
                lastName = "Doe",
                isLoading = true
            )
        }
    }
}