package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.common.ui.view.theme.AppColors
import com.example.base.R
import com.example.base.util.shape.*
import com.example.common.ui.view.theme.overLineOnPrimary
import com.example.ui.jobs.util.ui.ImageButton

@Composable
fun JobTopBar(
    onMenuClicked: () -> Unit,
    filterResultList: SnapshotStateList<String>,
    param: (String) -> Boolean
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
    ) {

        SearchAndFilterJobs(onMenuClicked)

        SelectedJobFilterItems(filterResultList, param)

    }
}

@Composable
fun SearchAndFilterJobs(onMenuClicked: () -> Unit) {
    var searchText: String by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.spacing_2x),
                start = dimensionResource(id = R.dimen.spacing_2x),
                end = dimensionResource(id = R.dimen.spacing_2x)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ImageButton(
            modifier = Modifier.padding(horizontal = 4.dp),
            drawableResId = com.example.ui.jobs.R.drawable.ic_job_filter,
            contentDescription = "filter",
            onClick = { onMenuClicked.invoke() }
        )

        TextField(
            value = searchText, onValueChange = { searchText = it },
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .neu(
                    NeuAttrs(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = 6.dp,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Pressed(RoundedCorner(12.dp)),
                    )
                ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
            ),
            placeholder = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_search),
                        contentDescription = "Search",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "Search", style = MaterialTheme.typography.body1)
                }
            },
        )
    }
}

@Composable
fun SelectedJobFilterItems(
    filterResultList: SnapshotStateList<String>,
    param: (String) -> Boolean
) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(filterResultList) { item ->
            Button(modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.spacing_3x),
                    top = dimensionResource(id = R.dimen.spacing_base)
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                ),
                shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                onClick = { param(item) }) {
                Text(text = item, style = MaterialTheme.typography.overLineOnPrimary())
                Icon(
                    modifier = Modifier
                        .size(15.dp, 15.dp)
                        .padding(start = dimensionResource(id = R.dimen.spacing_base)),
                    painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_close),
                    contentDescription = "",
                    tint = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
}

