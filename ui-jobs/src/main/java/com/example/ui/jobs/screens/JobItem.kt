package com.example.ui.jobs.screens

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.base.JobDto
import com.example.common.ui.view.theme.captionSecondary
import com.example.common.ui.view.theme.h3Primary
import com.example.ui.jobs.models.JobInfoModel
import com.example.base.R as BaseR
import com.example.ui.jobs.R as UiJobsR

@Composable
fun JobItem(
    jobInfoView: JobDto,
    modifier: Modifier = Modifier,
    isLoading: Boolean,
) {

    if (isLoading) {
        LoadingShimmerItem(modifier)
    } else {
        JobItemInfo(modifier, jobInfoView)
    }
}


@Composable
fun LoadingShimmerItem(modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row {

            Box(
                modifier = Modifier
                    .size(dimensionResource(id = BaseR.dimen.spacing_25x))
                    .fillMaxSize()
                    .shimmerEffect()
                    .clip(MaterialTheme.shapes.medium)
            ) {
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimensionResource(id = BaseR.dimen.spacing_3x)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .padding(dimensionResource(id = BaseR.dimen.spacing_base))
                        .shimmerEffect()
                        .height(dimensionResource(id = BaseR.dimen.spacing_5x)),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(dimensionResource(id = BaseR.dimen.spacing_base))
                        .shimmerEffect()
                        .height(dimensionResource(id = BaseR.dimen.spacing_2x)),
                )


                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(dimensionResource(id = BaseR.dimen.spacing_base))
                        .shimmerEffect()
                        .height(dimensionResource(id = BaseR.dimen.spacing_2x)),
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .padding(dimensionResource(id = BaseR.dimen.spacing_base))
                        .shimmerEffect()
                        .height(dimensionResource(id = BaseR.dimen.spacing_2x)),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .padding(dimensionResource(id = BaseR.dimen.spacing_base))
                        .shimmerEffect()
                        .height(dimensionResource(id = BaseR.dimen.spacing_2x)),
                )
            }
        }
    }
}

@Composable
fun JobItemInfo(modifier: Modifier, jobInfoView: JobDto) {
    val colorBackGround = remember {
        mutableStateListOf<Color>(
            Color(235, 189, 75, 255),
            Color(104, 213, 128, 255),
            Color(226, 108, 108, 255),
        )
    }
    val uriHandler = LocalUriHandler.current
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable {
//            uriHandler.openUri(jobInfoView.description ?: "")
        }
        .background(MaterialTheme.colors.surface)
        .padding(
            horizontal = dimensionResource(id = BaseR.dimen.spacing_2x),
            vertical = dimensionResource(id = BaseR.dimen.spacing_base)
        ),
        shape = RoundedCornerShape(dimensionResource(id = BaseR.dimen.spacing_3x)),
        elevation = dimensionResource(id = BaseR.dimen.spacing_base)
    )
    {
        Row {

            Box(
                modifier = Modifier
                    .size(dimensionResource(id = BaseR.dimen.spacing_25x))
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.surface)
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(jobInfoView.logo)
                        .build(),
                    modifier = modifier.fillMaxSize(),
                    contentDescription = ""
                ) {
                    when (painter.state) {
                        is AsyncImagePainter.State.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.padding(
                                    dimensionResource(
                                        id = BaseR.dimen.spacing_4x
                                    )
                                )
                            )
                        }
                        is AsyncImagePainter.State.Error -> {
                            val randColor =
                                colorBackGround[(0..2).random()]
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(randColor),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Text(
                                    text = jobInfoView.companyName?.toCharArray()?.first()
                                        .toString(),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.h1,
                                )
                            }
                        }
                        else -> {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimensionResource(id = BaseR.dimen.spacing_3x)),
            ) {
                Text(
                    text = jobInfoView.companyName ?: "",
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 1,
                    style = MaterialTheme.typography.h3Primary(),
                    overflow = TextOverflow.Ellipsis
                )

                ItemInfo(
                    taskTitle = stringResource(id = UiJobsR.string.label_location_title),
                    taskName = jobInfoView.location
                )

                ItemInfo(
                    taskTitle = stringResource(id = UiJobsR.string.label_employmentType_title),
                    taskName = jobInfoView.employmentType
                )

                ItemInfo(
                    taskTitle = stringResource(id = UiJobsR.string.label_role_title),
                    taskName = jobInfoView.role
                )

                ItemInfo(
                    taskTitle = stringResource(id = UiJobsR.string.label_data_title),
                    jobInfoView.datePosted
                )
            }
        }
    }
}

@Composable
fun ItemInfo(taskTitle: String, taskName: String?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = taskTitle,
            modifier = Modifier,
            maxLines = 1,
            style = MaterialTheme.typography.captionSecondary(),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = taskName ?: "-",
            modifier = Modifier,
            maxLines = 1,
            style = MaterialTheme.typography.overline,
            overflow = TextOverflow.Ellipsis
        )
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}
