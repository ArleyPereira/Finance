package br.com.hellodev.design.presenter.components.icon.default

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.hellodev.core.enums.illustration.IllustrationType
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ADD
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ALERT
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ARROW_DOWN
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ARROW_LEFT
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ARROW_RIGHT
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_CALENDAR
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_CHECK
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_CHECK_SIMPLE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_CLASSIFY
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_CLOSE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_CV_RESUME
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EDIT_FILL
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EDIT_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EDUCATION
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EMAIL_FILL
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EMAIL_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_ERROR
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_EXPECTED_SALARY
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_FACEBOOK
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_FILTER
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_GITHUB
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_GOOGLE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_HIDE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_INFO_FILL
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_INFO_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_LANGUAGES
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_LOCATION_FILL
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_LOCATION_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_LOCK
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_MARK_FILL
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_MARK_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_PDF
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_PERSON
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_PHONE_FILL
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_PHONE_LINE
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_PROJECTS
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_RIGHT
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_SEARCH
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_SEND
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_SETTINGS
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_SHOW
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_SKILLS
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_SUCCESS
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_SUMMARY
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_UPLOAD
import br.com.hellodev.core.enums.illustration.IllustrationType.IC_WORK_EXPERIENCE
import br.com.hellodev.design.R

@Composable
fun getDrawableIllustration(
    type: IllustrationType
): Painter {
    return painterResource(
        when (type) {
            IC_FILTER -> R.drawable.ic_filter
            IC_EMAIL_FILL -> R.drawable.ic_email_fill
            IC_EMAIL_LINE -> R.drawable.ic_email_line
            IC_FACEBOOK -> R.drawable.ic_facebook
            IC_GOOGLE -> R.drawable.ic_google
            IC_GITHUB -> R.drawable.ic_github
            IC_HIDE -> R.drawable.ic_hide
            IC_SHOW -> R.drawable.ic_show
            IC_LOCK -> R.drawable.ic_lock
            IC_CLASSIFY -> R.drawable.ic_classify
            IC_SEARCH -> R.drawable.ic_search
            IC_EDIT_FILL -> R.drawable.ic_edit_fill
            IC_EDIT_LINE -> R.drawable.ic_edit_line
            IC_ADD -> R.drawable.ic_add
            IC_CALENDAR -> R.drawable.ic_calendar
            IC_RIGHT -> R.drawable.ic_right
            IC_MARK_FILL -> R.drawable.ic_mark_fill
            IC_MARK_LINE -> R.drawable.ic_mark_line
            IC_SEND -> R.drawable.ic_send
            IC_UPLOAD -> R.drawable.ic_upload
            IC_PDF -> R.drawable.ic_pdf
            IC_CLOSE -> R.drawable.ic_close
            IC_ERROR -> R.drawable.ic_error
            IC_SUCCESS -> R.drawable.ic_success
            IC_ARROW_LEFT -> R.drawable.ic_arrow_left
            IC_ARROW_RIGHT -> R.drawable.ic_arrow_right
            IC_ARROW_DOWN -> R.drawable.ic_arrow_down
            IC_PERSON -> R.drawable.ic_person_fill
            IC_PHONE_LINE -> R.drawable.ic_phone_line
            IC_PHONE_FILL -> R.drawable.ic_phone_fill
            IC_LOCATION_LINE -> R.drawable.ic_location_line
            IC_LOCATION_FILL -> R.drawable.ic_location_fill
            IC_SUMMARY -> R.drawable.ic_summary
            IC_EXPECTED_SALARY -> R.drawable.ic_expected_salary
            IC_WORK_EXPERIENCE -> R.drawable.ic_work_experience
            IC_EDUCATION -> R.drawable.ic_education
            IC_PROJECTS -> R.drawable.ic_projects
            IC_LANGUAGES -> R.drawable.ic_languages
            IC_SKILLS -> R.drawable.ic_skills
            IC_CV_RESUME -> R.drawable.ic_cv_resume
            IC_SETTINGS -> R.drawable.ic_settings
            IC_ALERT -> R.drawable.ic_alert
            IC_CHECK -> R.drawable.ic_check
            IC_CHECK_SIMPLE -> R.drawable.ic_check_simple
            IC_INFO_FILL -> R.drawable.ic_info_fill
            IC_INFO_LINE -> R.drawable.ic_info_line
        }
    )
}

@Composable
fun DefaultIcon(
    type: IllustrationType,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit = {}
) {
    val painter = painterResource(
        when (type) {
            IC_FILTER -> R.drawable.ic_filter
            IC_EMAIL_FILL -> R.drawable.ic_email_fill
            IC_FACEBOOK -> R.drawable.ic_facebook
            IC_GOOGLE -> R.drawable.ic_google
            IC_GITHUB -> R.drawable.ic_github
            IC_HIDE -> R.drawable.ic_hide
            IC_SHOW -> R.drawable.ic_show
            IC_LOCK -> R.drawable.ic_lock
            IC_CLASSIFY -> R.drawable.ic_classify
            IC_SEARCH -> R.drawable.ic_search
            IC_EDIT_FILL -> R.drawable.ic_edit_fill
            IC_EDIT_LINE -> R.drawable.ic_edit_line
            IC_CALENDAR -> R.drawable.ic_calendar
            IC_RIGHT -> R.drawable.ic_right
            IC_MARK_FILL -> R.drawable.ic_mark_fill
            IC_MARK_LINE -> R.drawable.ic_mark_line
            IC_SEND -> R.drawable.ic_send
            IC_UPLOAD -> R.drawable.ic_upload
            IC_PDF -> R.drawable.ic_pdf
            IC_CLOSE -> R.drawable.ic_close
            IC_ERROR -> R.drawable.ic_error
            IC_SUCCESS -> R.drawable.ic_success
            IC_ARROW_LEFT -> R.drawable.ic_arrow_left
            IC_ARROW_RIGHT -> R.drawable.ic_arrow_right
            IC_ARROW_DOWN -> R.drawable.ic_arrow_down
            IC_PERSON -> R.drawable.ic_person_fill
            IC_EMAIL_LINE -> R.drawable.ic_email_line
            IC_ADD -> R.drawable.ic_add
            IC_PHONE_LINE -> R.drawable.ic_phone_line
            IC_PHONE_FILL -> R.drawable.ic_phone_fill
            IC_LOCATION_LINE -> R.drawable.ic_location_line
            IC_LOCATION_FILL -> R.drawable.ic_location_fill
            IC_SUMMARY -> R.drawable.ic_summary
            IC_EXPECTED_SALARY -> R.drawable.ic_expected_salary
            IC_WORK_EXPERIENCE -> R.drawable.ic_work_experience
            IC_EDUCATION -> R.drawable.ic_education
            IC_PROJECTS -> R.drawable.ic_projects
            IC_LANGUAGES -> R.drawable.ic_languages
            IC_SKILLS -> R.drawable.ic_skills
            IC_CV_RESUME -> R.drawable.ic_cv_resume
            IC_SETTINGS -> R.drawable.ic_settings
            IC_ALERT -> R.drawable.ic_settings
            IC_CHECK -> R.drawable.ic_check
            IC_CHECK_SIMPLE -> R.drawable.ic_check_simple
            IC_INFO_FILL -> R.drawable.ic_info_fill
            IC_INFO_LINE -> R.drawable.ic_info_line
        }
    )

    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            ),
        tint = tint
    )
}

@Preview
@Composable
private fun DefaultIconPreview() {
    DefaultIcon(
        modifier = Modifier,
        type = IC_FILTER,
        contentDescription = null,
        tint = Color.Unspecified,
        onClick = {}
    )
}