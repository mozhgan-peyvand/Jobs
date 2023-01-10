package com.example.ui_jobs.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.base.shape.*
import com.example.common_ui_view.JobInfoModel
import com.example.common_ui_view.R

@Composable
fun JobItem(
    item: JobInfoModel,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
) {

    Card(
        backgroundColor = Color(236, 234, 235),
        border = BorderStroke(2.dp, Color(236, 234, 235)),
        modifier = Modifier
            .padding(top = 16.dp)
            .neu(
                lightShadowColor = Color.White,
                darkShadowColor = Color.LightGray,
                lightSource = LightSource.LEFT_TOP,
                shape = Flat(RoundedCorner(24.dp)),
            ),
        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            Icon(
                modifier = modifier
                    .align(Alignment.TopEnd)
                    .padding(dimensionResource(id = com.example.base.R.dimen.spacing_3x))
                    .graphicsLayer {
                        scaleX = 1.3f
                        scaleY = 1.3f
                    }
                    .clickable { },
                painter = painterResource(id = com.example.ui_jobs.R.drawable.ic_baseline_star_border_24),
                contentDescription = "",
                tint = Color(177, 171, 174, 255),
            )
        }
//        val painter = rememberCoilPainter(item.imageUrl)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { },
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                contentScale = ContentScale.Fit,
                contentDescription = item.companyName,
                modifier = imageModifier
                    .size(130.dp)
                    .padding(8.dp)
            )

//            if (painter.loadState is ImageLoadState.Loading) {
//                CircularProgressIndicator(Modifier.align(Alignment.Center))
//            }
            Column {
                Text(

                    text = item.companyName ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    color = Color(36, 148, 158, 255),
                    maxLines = 1,
                    style = MaterialTheme.typography.h5,
                    overflow = TextOverflow.Ellipsis
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = item.locationCompany ?: "",
                        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                        color = Color.Black,
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                    )

                    Text(

                        text = item.employmentType ?: "",
                        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                        color = Color.Black,
                        maxLines = 1,
                        style = MaterialTheme.typography.subtitle2,
                    )

                }
                Text(
                    text = item.role ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 16.dp, top = 4.dp),
                    color = Color.Black,
                    maxLines = 2,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.description ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 16.dp, bottom = 8.dp, top = 4.dp),
                    color = Color.Black,
                    maxLines = 2,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}