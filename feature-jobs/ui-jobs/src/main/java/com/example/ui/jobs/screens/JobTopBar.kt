package com.example.ui.jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.base.R as BaseR
import com.example.ui.jobs.R as UiJobsR

@Composable
fun JobTopBar(
    actioner: (String) -> Unit,
    closeSearch: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        SearchAndFilterJobs(
            searchResultList = actioner,
            closeSearch = closeSearch
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAndFilterJobs(
    searchResultList: (String) -> Unit,
    closeSearch: (Boolean) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText by rememberSaveable {
        mutableStateOf("")
    }
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

        TextField(
            value = searchText, onValueChange = { searchText = it },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = BaseR.dimen.spacing_base))
                .fillMaxWidth()
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
                                searchText = ""
                                closeSearch.invoke(true)
                            }
                    )
                }
            },
            singleLine = true
        )
    }
}

