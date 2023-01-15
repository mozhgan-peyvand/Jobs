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
import com.example.base.shape.LightSource
import com.example.base.shape.Pressed
import com.example.base.shape.RoundedCorner
import com.example.base.shape.neu

@Composable
fun TopBar(
    onMenuClicked: () -> Unit,
    filterResultList: SnapshotStateList<String>,
    param: (String) -> Boolean
) {

    var searchText: String by remember {
        mutableStateOf("")
    }
    TopAppBar(
        modifier = Modifier
            .background(Color(236, 234, 235))
            .fillMaxWidth()
            .height(110.dp),
        backgroundColor = Color(236, 234, 235)
    ) {

        Column(
            modifier = Modifier
                .background(Color(236, 234, 235))
                .padding(
                    start = dimensionResource(id = R.dimen.spacing_2x),
                    end = dimensionResource(id = R.dimen.spacing_2x)
                )
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
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(236, 234, 235))
                        .neu(
                            lightShadowColor = Color.White,
                            darkShadowColor = Color.LightGray,
                            lightSource = LightSource.LEFT_TOP,
                            shape = Pressed(RoundedCorner(24.dp)),
                        )
                        .height(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(236, 234, 235),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent//hide the indicator
                    ),
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text(text = "search your word") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = com.example.base.R.drawable.ic_job_search),
                            contentDescription = "",
                            tint = Color(32, 184, 184, 255)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_close),
                            contentDescription = "",
                            tint = Color(32, 184, 184, 255)
                        )
                    }
                )
            }
            LazyRow(modifier = Modifier.fillMaxWidth()) {

                item {
                    Button(modifier = Modifier
                        .background(Color(236, 234, 235))
                        .padding(
                            start = dimensionResource(id = R.dimen.spacing_2x),
                            end = dimensionResource(id = R.dimen.spacing_2x)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(
                                236,
                                234,
                                235
                            )
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                        onClick = { onMenuClicked.invoke() }
                    ) {
                        Icon(
                            painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_filter),
                            contentDescription = "",
                            tint = Color(32, 184, 184, 255)
                        )
                        Text(text = "filter")
                    }

                }
                items(filterResultList) { item ->
                    Button(modifier = Modifier
                        .background(Color(236, 234, 235))
                        .padding(
                            start = dimensionResource(id = R.dimen.spacing_quarter_base),
                            end = dimensionResource(id = R.dimen.spacing_quarter_base)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =  Color(165, 241, 241, 255)
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                        onClick = { param(item) }) {

                        Text(text = item)
                        Icon(
                            painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_close),
                            contentDescription = "",
                            tint = Color(230, 230, 230, 255)
                        )
                    }
                }
            }
        }
    }
}