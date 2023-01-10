package com.example.ui_jobs.screens


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.common_ui_view.JobInfoModel
import com.example.base.R
import com.example.base.shape.LightSource
import com.example.base.shape.Pressed
import com.example.base.shape.RoundedCorner
import com.example.base.shape.neu

@Composable
fun JobScreen() {
    var jobList = listOf(
        JobInfoModel(
            1,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            2,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            3,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            4,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),

        JobInfoModel(
            4,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            4,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
        JobInfoModel(
            4,
            "part",
            "london",
            "part_time",
            "android_Developer",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        ),
    )

    Column(
        modifier = Modifier
            .background(Color(236, 234, 235))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id =R.dimen.spacing_2x)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Text(
                modifier = Modifier
                    .background(Color.Transparent)
                    .neu(
                        lightShadowColor = Color.White,
                        darkShadowColor = Color.LightGray,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Pressed(RoundedCorner(24.dp)),
                    ).weight(1f)
                    .padding(dimensionResource(id = R.dimen.spacing_4x)),
                text = "EnterCity",
            )


            Text(
                modifier = Modifier
                    .background(Color.Transparent)
                    .neu(
                        lightShadowColor = Color.White,
                        darkShadowColor = Color.LightGray,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Pressed(RoundedCorner(24.dp)),
                    ).weight(1f)
                    .padding(dimensionResource(id = R.dimen.spacing_4x)),
                text = "EnterRole"
            )
        }
        JobList(jobList)
    }
}

@Composable
private fun JobList(
    items: List<JobInfoModel>
) {
    val state: LazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(236, 234, 235)),
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            start = dimensionResource(id = R.dimen.spacing_4x),
            end = dimensionResource(id = R.dimen.spacing_4x),
            bottom = dimensionResource(id = R.dimen.spacing_4x)
        )
    ) {
        items(items) { item ->
            JobItem(
                item = item,
                modifier = Modifier,
                imageModifier = Modifier
            )

        }
    }
}


@Composable
fun Modifier.scrollbar(
    state: LazyListState,
    horizontal: Boolean,
    alignEnd: Boolean = true,
    thickness: Dp = 5.dp,
    fixedKnobRatio: Float? = null,
    knobCornerRadius: Dp = 4.dp,
    trackCornerRadius: Dp = 10.dp,
    knobColor: Color = Color(60, 223, 223, 255),
    trackColor: Color = Color(236, 234, 235),
    padding: Dp = 0.dp,
    visibleAlpha: Float = 1f,
    hiddenAlpha: Float = 0f,
    fadeInAnimationDurationMs: Int = 150,
    fadeOutAnimationDurationMs: Int = 500,
    fadeOutAnimationDelayMs: Int = 1000,
): Modifier {
    val targetAlpha =
        if (state.isScrollInProgress) {
            visibleAlpha
        } else {
            hiddenAlpha
        }
    val animationDurationMs =
        if (state.isScrollInProgress) {
            fadeInAnimationDurationMs
        } else {
            fadeOutAnimationDurationMs
        }
    val animationDelayMs =
        if (state.isScrollInProgress) {
            0
        } else {
            fadeOutAnimationDelayMs
        }

    val alpha by
    animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec =
        tween(delayMillis = animationDelayMs, durationMillis = animationDurationMs)
    )

    return drawWithContent {
        drawContent()

        state.layoutInfo.visibleItemsInfo.firstOrNull()?.let { firstVisibleItem ->
            if (state.isScrollInProgress || alpha > 0f) {
                // Size of the viewport, the entire size of the scrollable composable we are decorating with
                // this scrollbar.
                val viewportSize =
                    if (horizontal) {
                        size.width
                    } else {
                        size.height
                    } - padding.toPx() * 2

                // The size of the first visible item. We use this to estimate how many items can fit in the
                // viewport. Of course, this works perfectly when all items have the same size. When they
                // don't, the scrollbar knob size will grow and shrink as we scroll.
                val firstItemSize = firstVisibleItem.size

                // The *estimated* size of the entire scrollable composable, as if it's all on screen at
                // once. It is estimated because it's possible that the size of the first visible item does
                // not represent the size of other items. This will cause the scrollbar knob size to grow
                // and shrink as we scroll, if the item sizes are not uniform.
                val estimatedFullListSize = firstItemSize * state.layoutInfo.totalItemsCount

                // The difference in position between the first pixels visible in our viewport as we scroll
                // and the top of the fully-populated scrollable composable, if it were to show all the
                // items at once. At first, the value is 0 since we start all the way to the top (or start
                // edge). As we scroll down (or towards the end), this number will grow.
                val viewportOffsetInFullListSpace =
                    state.firstVisibleItemIndex * firstItemSize + state.firstVisibleItemScrollOffset

                // Where we should render the knob in our composable.
                val knobPosition =
                    (viewportSize / estimatedFullListSize) * viewportOffsetInFullListSpace + padding.toPx()
                // How large should the knob be.
                val knobSize =
                    fixedKnobRatio?.let { it * viewportSize }
                        ?: ((viewportSize * viewportSize) / estimatedFullListSize)

                // Draw the track
                drawRoundRect(
                    color = trackColor,
                    topLeft = Offset(padding.toPx(), 0f),
                    size =
                    if (horizontal) {
                        Size(size.width - padding.toPx() * 2, thickness.toPx())
                    } else {
                        Size(thickness.toPx(), size.height - padding.toPx() * 2)
                    },
                    alpha = alpha,
                    cornerRadius = CornerRadius(
                        x = trackCornerRadius.toPx(),
                        y = trackCornerRadius.toPx()
                    ),
                )

                // Draw the knob
                drawRoundRect(
                    color = knobColor,
                    topLeft =
                    when {
                        // When the scrollbar is vertical and aligned to the end:
                        alignEnd -> Offset(size.width - thickness.toPx(), knobPosition)
                        // When the scrollbar is vertical and aligned to the start:
                        else -> Offset(0f, knobPosition)
                    },
                    size =
                    Size(thickness.toPx(), knobSize),
                    alpha = alpha,
                    cornerRadius = CornerRadius(
                        x = knobCornerRadius.toPx(),
                        y = knobCornerRadius.toPx()
                    )
                )
            }
        }
    }
}
