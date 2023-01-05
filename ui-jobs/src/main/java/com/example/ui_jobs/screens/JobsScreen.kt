package com.example.ui_jobs.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.common_ui_view.R
import com.example.common_ui_view.JobCard
import com.example.common_ui_view.SlideState

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun JobScreen() {
    var jobList = arrayOf(
        com.example.common_ui_view.JobInfoModel(1, "android1", "description1", 1),
        com.example.common_ui_view.JobInfoModel(2, "android2", "description2", 1),
        com.example.common_ui_view.JobInfoModel(3, "android3", "description3", 1),
        com.example.common_ui_view.JobInfoModel(4, "android4", "description4", 1),
        com.example.common_ui_view.JobInfoModel(5, "android5", "description5", 1),
        com.example.common_ui_view.JobInfoModel(6, "android6", "description6", 1),
        com.example.common_ui_view.JobInfoModel(7, "android7", "description7", 1),

    )
    val shoesArticles = remember { mutableStateListOf(*jobList) }
    val slideStates = remember {
        mutableStateMapOf<com.example.common_ui_view.JobInfoModel, SlideState>()
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
    shoesArticles: MutableList<com.example.common_ui_view.JobInfoModel>,
    slideStates: Map<com.example.common_ui_view.JobInfoModel, SlideState>,
    updateSlideState: (shoesArticle: com.example.common_ui_view.JobInfoModel, slideState: SlideState) -> Unit,
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