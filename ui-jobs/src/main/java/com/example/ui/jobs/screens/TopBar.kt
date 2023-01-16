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
import com.example.base.R
import com.example.base.shape.*
import com.example.base.AppColors
import com.example.common.ui.view.ImageButton

@Composable
fun TopBar(
    onMenuClicked: () -> Unit,
    filterResultList: SnapshotStateList<String>,
    param: (String) -> Boolean,
    changeTheme: () -> Unit,
    isDarkTheme: MutableState<Boolean>
) {

    var searchText: String by remember {
        mutableStateOf("")
    }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)

        ) {
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
                    modifier = Modifier.padding(4.dp).weight(1f),
                    drawableResId = com.example.ui.jobs.R.drawable.ic_job_filter,
                    contentDescription = "filter",
                    onClick = { onMenuClicked.invoke() }
                )

                TextField(
                    value = searchText, onValueChange = { searchText = it },
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(4f)
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
                                painter = painterResource(id = R.drawable.ic_job_search),
                                contentDescription = "Search",
                                tint = MaterialTheme.colors.primary
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = "Search")
                        }
                    },
                )
                TitleWithThemeToggle(
                    Modifier.weight(1f),
                    isDarkTheme = isDarkTheme,
                    onThemeToggle = changeTheme
                )

            }
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(filterResultList) { item ->
                    Button(modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.spacing_quarter_base),
                            end = dimensionResource(id = R.dimen.spacing_quarter_base)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                        onClick = { param(item) }) {

                        Text(text = item, color = MaterialTheme.colors.onPrimary)
                        Icon(
                            painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_close),
                            contentDescription = "",
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }
                }
            }
        }
}

@Composable
fun TitleWithThemeToggle(
    modifier: Modifier,
    isDarkTheme: MutableState<Boolean>,
    onThemeToggle: () -> Unit
) {
    ImageButton(
        modifier = modifier.padding(4.dp),
        drawableResId = if (isDarkTheme.value) com.example.ui.jobs.R.drawable.ic_baseline_light_mode
        else com.example.ui.jobs.R.drawable.ic_baseline_dark_mode_24,
        contentDescription = "Toggle theme",
        onClick = onThemeToggle
    )
}