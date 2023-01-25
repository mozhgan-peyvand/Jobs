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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.common.ui.view.theme.captionOnBackground
import com.example.common.ui.view.theme.h3Primary
import com.example.ui.jobs.models.JobInfoModel

@Composable
fun JobItem(
    item: JobInfoModel,
    modifier: Modifier = Modifier,
    isloading: Boolean,
) {
    val colorBackGround = remember {
        mutableStateListOf<Color>(
            Color(235, 189, 75, 255),
            Color(104, 213, 128, 255),
            Color(226, 108, 108, 255),
        )
    }
    if (isloading) {
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
                        .size(100.dp)
                        .fillMaxSize()
                        .shimmerEffect()
                        .clip(MaterialTheme.shapes.medium)
                ) {
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .padding(4.dp)
                            .shimmerEffect()
                            .height(20.dp),

                        )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(4.dp)
                            .shimmerEffect()
                            .height(10.dp),

                        )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(4.dp)
                            .shimmerEffect()
                            .height(10.dp),

                        )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(4.dp)
                            .shimmerEffect()
                            .height(10.dp),

                        )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .padding(4.dp)
                            .shimmerEffect()
                            .height(10.dp),

                        )
                }

            }
        }

    } else {
        val uriHandler = LocalUriHandler.current
        Card(modifier = modifier
            .fillMaxWidth()
            .clickable {
                uriHandler.openUri(item.description ?: "")
            }
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 8.dp, vertical = 4.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp
        )
        {
            Row {

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface)
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.imageRes)
                            .build(),
                        modifier = modifier.fillMaxSize(),
                        contentDescription = ""
                    ) {
                        when (painter.state) {
                            is AsyncImagePainter.State.Loading -> {
                                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
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
                                        text = item.companyName?.toCharArray()?.first().toString(),
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
                        .padding(start = 12.dp),
                ) {
                    Text(
                        text = item.companyName ?: "",
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 1,
                        style = MaterialTheme.typography.h3Primary(),
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = item.locationCompany ?: "",
                        maxLines = 1,
                        style = MaterialTheme.typography.captionOnBackground(),
                    )

                    Text(
                        text = item.employmentType ?: "",
                        maxLines = 1,
                        style = MaterialTheme.typography.captionOnBackground(),
                    )

                    Text(
                        text = item.role ?: "",
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 1,
                        style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = item.data ?: "",
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 1,
                        style = MaterialTheme.typography.overline,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
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