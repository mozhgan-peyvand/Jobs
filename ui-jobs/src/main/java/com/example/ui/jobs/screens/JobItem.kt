package com.example.ui.jobs.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.common.ui.view.R
import com.example.common.ui.view.theme.captionOnBackground
import com.example.common.ui.view.theme.h3OnPrimary
import com.example.ui.jobs.models.JobInfoModel

@Composable
fun JobItem(
    item: JobInfoModel,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
) {

    Card(
        backgroundColor = MaterialTheme.colors.surface,
        border = BorderStroke(2.dp, MaterialTheme.colors.surface),
        modifier = Modifier
            .padding(top = 4.dp),
        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
        elevation = 4.dp
    ) {
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

            Column {
                Text(

                    text = item.companyName ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    maxLines = 1,
                    style = MaterialTheme.typography.h3OnPrimary(),
                    overflow = TextOverflow.Ellipsis
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = item.locationCompany ?: "",
                        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                        maxLines = 1,
                        style = MaterialTheme.typography.captionOnBackground(),
                    )

                    Text(
                        text = item.employmentType ?: "",
                        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                        maxLines = 1,
                        style = MaterialTheme.typography.captionOnBackground(),
                    )
                }
                Text(
                    text = item.role ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 16.dp, top = 4.dp),
                    maxLines = 2,
                    style = MaterialTheme.typography.subtitle2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.description ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 16.dp, bottom = 8.dp, top = 4.dp),
                    maxLines = 2,
                    style = MaterialTheme.typography.overline,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}