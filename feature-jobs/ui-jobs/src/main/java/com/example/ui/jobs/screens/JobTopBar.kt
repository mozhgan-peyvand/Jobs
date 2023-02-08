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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.common.ui.view.theme.AppColors
import com.example.base.util.shape.*
import com.example.common.ui.view.theme.overLineOnPrimary
import com.example.ui.jobs.util.ui.ImageButton
import com.example.base.R as BaseR
import com.example.ui.jobs.R as UiJobsR

@Composable
fun JobTopBar(
    onClickedFilterJobs: () -> Unit,
    filterResultList: SnapshotStateList<String>,
    actioner: (String) -> Unit,
    closeSearch: (Boolean) -> Unit,
    searchText: String,
    onChangeSearchText: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
    ) {

        SearchAndFilterJobs(
            onMenuClicked = onClickedFilterJobs,
            searchResultList = actioner,
            closeSearch = closeSearch,
            searchText = searchText,
            onChangeSearchText = onChangeSearchText
        )

        SelectedJobFilterItems(filterResultList)

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAndFilterJobs(
    onMenuClicked: () -> Unit,
    searchResultList: (String) -> Unit,
    closeSearch: (Boolean) -> Unit,
    searchText: String,
    onChangeSearchText: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    if (searchText.isEmpty()) {
        searchResultList.invoke("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = BaseR.dimen.spacing_2x),
                start = dimensionResource(id = BaseR.dimen.spacing_2x),
                end = dimensionResource(id = BaseR.dimen.spacing_2x)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = BaseR.dimen.spacing_base))
    ) {
        ImageButton(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = BaseR.dimen.spacing_base)),
            drawableResId = com.example.ui.jobs.R.drawable.ic_job_filter,
            contentDescription = "filter button",
            onClick = { onMenuClicked.invoke() }
        )

        TextField(
            value = searchText, onValueChange = { onChangeSearchText.invoke(it) },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = BaseR.dimen.spacing_base))
                .neu(
                    NeuAttrs(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = dimensionResource(id = BaseR.dimen.spacing_2x),
                        lightSource = LightSource.LEFT_TOP,
                        shape = Pressed(RoundedCorner(dimensionResource(id = BaseR.dimen.spacing_3x))),
                    )
                ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
            ),
            keyboardActions = KeyboardActions(onDone = {
                closeSearch.invoke(false)
                searchResultList.invoke(searchText)
                keyboardController?.hide()

            }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            placeholder = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = UiJobsR.drawable.ic_job_search),
                        contentDescription = "Search",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.size(dimensionResource(id = BaseR.dimen.spacing_base)))
                    Text(
                        text = stringResource(id = UiJobsR.string.label_search_job),
                        style = MaterialTheme.typography.body1
                    )
                }
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = UiJobsR.drawable.ic_job_close),
                        contentDescription = "Search",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(dimensionResource(id = BaseR.dimen.spacing_5x))
                            .clickable {
                                searchResultList.invoke("")
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
                        start = dimensionResource(id = BaseR.dimen.spacing_3x),
                        top = dimensionResource(id = BaseR.dimen.spacing_base)
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(dimensionResource(id = BaseR.dimen.spacing_3x))),
                    onClick = {}
                ) {
                    Text(text = item, style = MaterialTheme.typography.overLineOnPrimary())
                }
            }
        }
    }
}

