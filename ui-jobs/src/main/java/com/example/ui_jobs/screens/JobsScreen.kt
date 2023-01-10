package com.example.ui_jobs.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.common_ui_view.JobInfoModel
import com.example.common_ui_view.R
import com.example.common_ui_view.normalizedItemPosition
import com.example.base.shape.Punched
import com.example.base.shape.neumorphic
import kotlin.math.absoluteValue

@Composable
fun JobScreen(navigateToJobDetailScreen: () -> Unit) {
    var jobList = listOf(
        JobInfoModel(
            1,
            "android1",
            "desceeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeripfffffffffffffffffffffffffffftion1",
            1
        ),
        JobInfoModel(2, "android2", "description2", 1),
        JobInfoModel(3, "android3", "description3", 1),
        JobInfoModel(4, "android4", "description4", 1),
        JobInfoModel(5, "android5", "description5", 1),
        JobInfoModel(6, "android6", "description6", 1),
        JobInfoModel(7, "android7", "description7", 1),

        )

    ScaleFadeImageCardRow(jobList, navigateToJobDetailScreen)

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ScaleFadeImageCardRow(
    items: List<JobInfoModel>,
    navigateToJobDetailScreen: () -> Unit
) {
    val state: LazyListState = rememberLazyListState()
    LazyColumn(
        modifier= Modifier.background(Color(236, 234, 235)),
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        items(items, key = { it.id }) { item ->
            ImageCard(
                navigateToJobDetailScreen,
                item,
                modifier = Modifier
                    .animateItemPlacement()
                    .graphicsLayer {
                        val value =
                            1 - (state.layoutInfo.normalizedItemPosition(item.id).absoluteValue * 0.099F)
                        alpha = value
                        scaleX = value
                        scaleY = value
                    },
                imageModifier = Modifier
            )

        }
    }
}

@Composable
private fun ImageCard(
    navigateToJobDetailScreen: () -> Unit,
    item: JobInfoModel,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
) {

    Card(
        backgroundColor = Color(236, 234, 235),
        border = BorderStroke(2.dp, Color(236, 234, 235)),
        modifier = Modifier
            .padding( top = 16.dp)
            .clickable {
                navigateToJobDetailScreen.invoke()
            }
            .neumorphic(
                neuShape =
//                // Punched shape
                Punched.Rounded(radius = 16.dp)
//
////                        Punched.Oval()
////
////                        // Presssed Shape
//                        Pressed.Rounded(radius =4.dp)
////                        Pressed.Oval()
////
////                        // Pot shape
//                        Pot.Rounded(radius = 8.dp)
//                        Pot.Oval()
            ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 0.dp
    ) {
//        val painter = rememberCoilPainter(item.imageUrl)
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                contentScale = ContentScale.Fit,
                contentDescription = item.name,
                modifier = imageModifier
                    .size(130.dp)
                    .padding(8.dp)
            )
//            if (painter.loadState is ImageLoadState.Loading) {
//                CircularProgressIndicator(Modifier.align(Alignment.Center))
//            }
            Column {
                Text(
                    text = item.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 8.dp),
                    color = Color.Black,
                    maxLines = 1,
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp),
                    color = Color.Black,
                    maxLines = 2,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}