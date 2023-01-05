package com.example.ui_favorite

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.common_ui_view.JobCard
import com.example.common_ui_view.JobInfoModel
import com.example.common_ui_view.R
import com.example.common_ui_view.SlideState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoriteScreen() {
    var jobList = arrayOf(
        JobInfoModel(1, "android1", "description1", 1),
        JobInfoModel(2, "android2", "description2", 1),
        JobInfoModel(3, "android3", "description3", 1)
        )
    val shoesArticles = remember { mutableStateListOf(*jobList) }
    val slideStates = remember {
        mutableStateMapOf<JobInfoModel, SlideState>()
            .apply {
                shoesArticles.map { shoesArticle ->
                    shoesArticle to SlideState.NONE
                }.toMap().also {
                    putAll(it)
                }
            }
    }
    ShoesList(
        shoesArticles = shoesArticles,
        slideStates = slideStates,
        updateSlideState = { shoesArticle, slideState -> slideStates[shoesArticle] = slideState },
        updateItemPosition = { currentIndex, destinationIndex ->
            val shoesArticle = shoesArticles[currentIndex]
            shoesArticles.removeAt(currentIndex)
            shoesArticles.add(destinationIndex, shoesArticle)
            slideStates.apply {
                shoesArticles.map { shoesArticle ->
                    shoesArticle to SlideState.NONE
                }.toMap().also {
                    putAll(it)
                }
            }
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun ShoesList(
    shoesArticles: MutableList<JobInfoModel>,
    slideStates: Map<JobInfoModel, SlideState>,
    updateSlideState: (shoesArticle: JobInfoModel, slideState: SlideState) -> Unit,
    updateItemPosition: (currentIndex: Int, destinationIndex: Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.list_top_padding))
    ) {
        items(shoesArticles.size) { index ->
            val shoesArticle = shoesArticles.getOrNull(index)
            if (shoesArticle != null) {
                key(shoesArticle) {
                    val slideState = slideStates[shoesArticle] ?: SlideState.NONE
                    JobCard(
                        shoesArticle = shoesArticle,
                        slideState = slideState,
                        shoesArticles = shoesArticles,
                        updateSlideState = updateSlideState,
                        updateItemPosition = updateItemPosition
                    )
                }
            }
        }
    }
}