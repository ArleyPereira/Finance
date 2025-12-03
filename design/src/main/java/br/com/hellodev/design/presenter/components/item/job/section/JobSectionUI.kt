package br.com.hellodev.design.presenter.components.item.job.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.domain.model.category.CategoryDomain
import br.com.hellodev.domain.model.job.section.JobSectionDomain
import br.com.hellodev.design.presenter.components.empty.EmptyUI
import br.com.hellodev.design.presenter.components.item.job.item.JobItemUI
import br.com.hellodev.design.presenter.components.tag.category.CategoryTagUI
import br.com.hellodev.design.presenter.theme.HelloTheme
import br.com.hellodev.design.presenter.theme.ColorScheme
import br.com.hellodev.design.presenter.theme.UrbanistFamily

@Composable
fun HorizontalJobSectionUI(
    section: JobSectionDomain? = null,
    modifier: Modifier = Modifier,
    onSaveClick: (Int) -> Unit,
    onJobClick: (Int) -> Unit,
    onAllClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        JobHeaderSection(
            leftTitle = section?.leftTitle,
            rightTitle = section?.rightTitle,
            onClick = onAllClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(
                items = section?.items ?: emptyList(),
                key = { it.id ?: 0 }
            ) { job ->
                JobItemUI(
                    modifier = Modifier
                        .fillParentMaxWidth(0.95f),
                    job = job,
                    onSaveClick = onSaveClick,
                    onClick = onJobClick
                )
            }
        }
    }
}

fun LazyListScope.verticalJobSectionUI(
    section: JobSectionDomain? = null,
    categorySelected: CategoryDomain? = null,
    search: String? = "",
    onCategorySelected: (CategoryDomain?) -> Unit,
    onSaveClick: (Int) -> Unit,
    onJobClick: (Int) -> Unit,
    onAllClick: () -> Unit
) {
    item {
        Spacer(modifier = Modifier.height(8.dp))

        JobHeaderSection(
            leftTitle = section?.leftTitle,
            rightTitle = section?.rightTitle,
            onClick = onAllClick
        )
    }

    item {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(
                items = section?.categories ?: emptyList(),
                key = { it.id ?: 0 }
            ) { category ->
                CategoryTagUI(
                    text = category.name ?: "",
                    isSelect = category.id == categorySelected?.id,
                    onClick = { onCategorySelected(category) }
                )
            }
        }
    }

    if (section?.items?.isEmpty() == true) {
        item {
            EmptyUI(
                title = "Nenhuma vaga encontrada",
                description = "Com o termo pesquisado: $search",
            )
        }
    }

    items(
        items = section?.items ?: emptyList(),
        key = { it.id ?: 0 }
    ) { job ->
        JobItemUI(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            job = job,
            onSaveClick = onSaveClick,
            onClick = onJobClick
        )
    }
}

@Composable
fun JobHeaderSection(
    modifier: Modifier = Modifier,
    rightTitle: String?,
    leftTitle: String?,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = leftTitle ?: "",
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = ColorScheme.colorScheme.text.primaryColor
            )
        )

        Text(
            text = rightTitle ?: "",
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onClick
                ),
            style = TextStyle(
                lineHeight = 22.4.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = ColorScheme.colorScheme.defaultColor,
                letterSpacing = 0.2.sp
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun JobSectionUIPreview() {
    HelloTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorScheme.colorScheme.screen.backgroundPrimary),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HorizontalJobSectionUI(
                section = JobSectionDomain.Companion.items[0],
                modifier = Modifier
                    .padding(top = 24.dp),
                onSaveClick = {},
                onJobClick = {},
                onAllClick = {}
            )
        }
    }
}