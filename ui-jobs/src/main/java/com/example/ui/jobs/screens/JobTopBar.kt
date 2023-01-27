package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.common.ui.view.theme.AppColors
import com.example.base.R
import com.example.base.Success
import com.example.base.util.shape.*
import com.example.common.ui.view.theme.overLineOnPrimary
import com.example.ui.jobs.models.JobInfoModel
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.toViewJob
import com.example.ui.jobs.util.ui.ImageButton

@Composable
fun JobTopBar(
    onClickedFilterJobs: () -> Unit,
    filterResultList: SnapshotStateList<String>,
    searchResultList: SnapshotStateList<JobInfoModel>,
    viewState: JobScreenState,
    closeSearch: (Boolean) -> Unit,
    searchText: String,
    onChangeSearchText: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
    ) {

        SearchAndFilterJobs(
            onClickedFilterJobs,
            searchResultList,
            viewState,
            closeSearch,
            searchText,
            onChangeSearchText
        )

        SelectedJobFilterItems(filterResultList)

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAndFilterJobs(
    onMenuClicked: () -> Unit,
    searchResultList: SnapshotStateList<JobInfoModel>,
    viewState: JobScreenState,
    closeSearch: (Boolean) -> Unit,
    searchText: String,
    onChangeSearchText: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    if (searchText.isEmpty()){
        searchResultList.clear()
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
            modifier = Modifier
                .padding(horizontal = 4.dp),
            drawableResId = com.example.ui.jobs.R.drawable.ic_job_filter,
            contentDescription = "filter",
            onClick = { onMenuClicked.invoke() }
        )

        TextField(
            value = searchText, onValueChange = { onChangeSearchText.invoke(it) },
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
            keyboardActions = KeyboardActions(onDone = {
                closeSearch.invoke(false)
                (viewState.allJobList as Success).invoke()?.map { item ->
                    if (
                        item.company_name?.contains(searchText) == true ||
                        item.location?.contains(searchText) == true ||
                        item.role?.contains(searchText) == true
                    ) {
                        searchResultList.add(item.toViewJob())
                    }
                }
                keyboardController?.hide()

            }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
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
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_close),
                        contentDescription = "Search",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                searchResultList.clear()
                                onChangeSearchText.invoke("")
                                closeSearch.invoke(true)
                            }
                    )
                }
            },
            singleLine = true
        )
    }
}

@Composable
fun SelectedJobFilterItems(
    filterResultList: SnapshotStateList<String>
) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(filterResultList) { item ->
            if (item.isNotEmpty()) {
                Button(modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.spacing_3x),
                        top = dimensionResource(id = R.dimen.spacing_base)
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                    onClick = {}
                ) {
                    Text(text = item, style = MaterialTheme.typography.overLineOnPrimary())
                }
            }
        }
    }
}

