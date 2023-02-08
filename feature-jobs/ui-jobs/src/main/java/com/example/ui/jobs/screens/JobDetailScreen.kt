package com.example.ui.jobs.screens

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.base.R as BaseR
import com.example.ui.jobs.R as JobR
import com.example.base.util.Success
import com.example.common.ui.view.theme.captionSecondary
import com.example.common.ui.view.theme.h3Primary
import com.example.ui.jobs.models.JobDetailScreenState
import com.example.ui.jobs.models.JobDetailScreenUiEvent

@Composable
fun JobDetailScreen(viewModel: JobDetailViewModel, jobItemId: String) {

    val viewState by viewModel.stateFlow.collectAsState(initial = JobDetailScreenState())

    JobDetail(
        actioner = { action ->
            viewModel.submitAction(action)
        },
        viewState
    )
}

@Composable
fun JobDetail(
    actioner: (JobDetailScreenUiEvent) -> Unit,
    viewState: JobDetailScreenState
) {
    val uriHandler = LocalUriHandler.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(dimensionResource(id = BaseR.dimen.spacing_4x))
            .verticalScroll(scrollState)
    ) {
        if (viewState.jobDetailInfo is Success) {
            HtmlText(
                html = viewState.jobDetailInfo.invoke()
            )
            Button(onClick = {
//                uriHandler.openUri(jobDto.description ?: "")
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onSecondary)) {
                Text(
                    modifier = Modifier
                        .padding(dimensionResource(id = BaseR.dimen.spacing_2x)),
                    text = stringResource(id = JobR.string.label_buttom_more_information),
                    style = MaterialTheme.typography.captionSecondary()
                )
            }
        }
    }
}

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    val compatContext = LocalContext.current
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).apply {
                setTextColor(compatContext.getColor(JobR.color.jobDetailColor))
            }
        },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}

const val KEY_ARTICLE_ID = "article_id"