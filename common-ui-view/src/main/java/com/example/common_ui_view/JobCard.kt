package com.example.common_ui_view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


private val particlesStreamRadii = mutableListOf<Float>()
private var itemHeight = 0
private var particleRadius = 0f
private var slotItemDifference = 0f

@ExperimentalAnimationApi
@Composable
fun JobCard(
    shoesArticle: JobInfoModel,
    slideState: SlideState,
    shoesArticles: MutableList<JobInfoModel>,
    updateSlideState: (shoesArticle: JobInfoModel, slideState: SlideState) -> Unit,
    updateItemPosition: (currentIndex: Int, destinationIndex: Int) -> Unit,
    navigateToJobDetailScreen: (() -> Unit)? = null,
    lazyListState: LazyListState,
    modifier: Modifier
) {
    val itemHeightDp = dimensionResource(id = R.dimen.image_size)
    with(LocalDensity.current) {
        itemHeight = itemHeightDp.toPx().toInt()
        particleRadius = dimensionResource(id = R.dimen.particle_radius).toPx()
        if (particlesStreamRadii.isEmpty())
            particlesStreamRadii.addAll(arrayOf(6.dp.toPx(), 10.dp.toPx(), 14.dp.toPx()))
        slotItemDifference = 18.dp.toPx()
    }
    val verticalTranslation by animateIntAsState(
        targetValue = when (slideState) {
            SlideState.UP -> -itemHeight
            SlideState.DOWN -> itemHeight
            else -> 0
        },
    )
    val isDragged = remember { mutableStateOf(false) }
    val zIndex = if (isDragged.value) 1.0f else 0.0f
    val rotation = if (isDragged.value) -5.0f else 0.0f

    val currentIndex = remember { mutableStateOf(0) }
    val destinationIndex = remember { mutableStateOf(0) }

    val isPlaced = remember { mutableStateOf(false) }

    LaunchedEffect(isPlaced.value) {
        if (isPlaced.value) {
            launch {
                if (currentIndex.value != destinationIndex.value) {
                    updateItemPosition(currentIndex.value, destinationIndex.value)
                }
                isPlaced.value = false
            }
        }
    }

    Box(
        modifier
            .padding(horizontal = 16.dp)
            .dragToReorder(
                shoesArticle,
                shoesArticles,
                itemHeight,
                updateSlideState,
                isDraggedAfterLongPress = true,
                { isDragged.value = true },
                { cIndex, dIndex ->
                    isDragged.value = false
                    isPlaced.value = true
                    currentIndex.value = cIndex
                    destinationIndex.value = dIndex
                }
            )
            .clickable { }
            .offset { IntOffset(0, verticalTranslation) }
            .zIndex(zIndex)
            .rotate(rotation)
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { navigateToJobDetailScreen?.invoke() }
                .graphicsLayer {
                    val value =
                        1 - (lazyListState.layoutInfo.normalizedItemPosition(shoesArticle.id).absoluteValue * 0.15F)
                    alpha = value
                    scaleX = value
                    scaleY = value
                },
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .graphicsLayer {
                        val value =
                            1 - (lazyListState.layoutInfo.normalizedItemPosition(shoesArticle.id).absoluteValue * 0.15F)
                        alpha = value
                        scaleX = value
                        scaleY = value
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit,
                )
                Column(Modifier.padding(8.dp)) {
                    Text(
                        text = shoesArticle.name,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onSurface,
                    )
                    Text(
                        text = shoesArticle.description,
                        style = MaterialTheme.typography.body2,
                    )
                }
            }
        }
    }
}

fun LazyListLayoutInfo.normalizedItemPosition(key: Any): Float =
    visibleItemsInfo
        .firstOrNull { it.key == key }
        ?.let {
            val center = (viewportEndOffset + viewportStartOffset - it.size) / 2F
            (it.offset.toFloat() - center) / center
        }
        ?: 0F
