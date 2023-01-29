package com.example.ui.jobs.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.common.ui.view.theme.AppColors
import com.example.base.util.shape.*
import com.example.ui.jobs.models.JobScreenState
import kotlinx.coroutines.launch
import com.example.base.R as BaseR
import com.example.ui.jobs.R as UiJobsR

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterJobs(
    onCloseFilterJobsDrawer: () -> Unit,
    filterJobList: (String?, String?) -> Unit,
    viewState: JobScreenState,
    filterResultList: SnapshotStateList<String>,
    closeSearch: (Boolean) -> Unit,
    onChangeSearchText: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )


    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }
    var selectedItem by remember {
        mutableStateOf(-1)
    }
    var cityText by remember {
        mutableStateOf(filterResultList[1])
    }
    var roleText by remember {
        mutableStateOf(filterResultList[0])
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            when (selectedItem) {
                0 -> RoleContentBottomSheet(
                    roleText = roleText,
                    sheetStateHide = { coroutineScope.launch { sheetState.hide() } },
                    onChangeRoleText = { roleText = it },
                    roleList = viewState.allRoleList.invoke() ?: listOf()
                )
                1 -> CityContentBottomSheet(
                    cityText = cityText,
                    sheetStateHide = { coroutineScope.launch { sheetState.hide() } },
                    onChangeLocationText = { cityText = it },
                    locationList = viewState.allLocationList.invoke() ?: listOf()
                )
                else -> {
                    val defaultBottomSheetList = listOf("")
                    Column(
                        Modifier
                            .selectableGroup()
                            .padding(dimensionResource(id = BaseR.dimen.spacing_8x))
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        defaultBottomSheetList.forEach { text ->
                            Text(text, style = MaterialTheme.typography.subtitle1)
                        }
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(dimensionResource(id = BaseR.dimen.spacing_6x)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = UiJobsR.string.label_choose_filter_items),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = BaseR.dimen.spacing_8x)))

            RoleField(roleText, sheetState,{ roleText = it }) { selectedItem = 0 }

            Spacer(modifier = Modifier.height(dimensionResource(id = BaseR.dimen.spacing_8x)))

            CityField(cityText, sheetState,{ cityText = it }) { selectedItem = 1 }

            Spacer(modifier = Modifier.height(dimensionResource(id = BaseR.dimen.spacing_8x)))

            Button(
                modifier = Modifier,
                onClick = {
                    closeSearch.invoke(true)
                    onChangeSearchText.invoke("")
                    filterJobList.invoke(roleText , cityText)
                    filterResultList.clear()
                    filterResultList.addAll(listOf(roleText, cityText))
                    onCloseFilterJobsDrawer.invoke()
                },
                shape = RoundedCornerShape(CornerSize(dimensionResource(id = BaseR.dimen.spacing_6x))),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                ),
                elevation = ButtonDefaults.elevation(dimensionResource(id = BaseR.dimen.spacing_2x))
            ) {
                Text(text = stringResource(id = UiJobsR.string.label_filter_job_button), style = MaterialTheme.typography.h3)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoleField(
    roleText: String,
    sheetState: ModalBottomSheetState,
    setRoleText: (String) -> Unit,
    setItemSelected: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()

    TextField(
        value = roleText, onValueChange = { setRoleText(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = BaseR.dimen.spacing_base))
            .neu(
                NeuAttrs(
                    lightShadowColor = AppColors.lightShadow(),
                    darkShadowColor = AppColors.darkShadow(),
                    shadowElevation = dimensionResource(id = BaseR.dimen.spacing_2x),
                    lightSource = LightSource.LEFT_TOP,
                    shape = Pressed(RoundedCorner(dimensionResource(id = BaseR.dimen.spacing_3x))),
                )
            )
            .clickable {
                coroutineScope.launch {
                    setItemSelected.invoke()
                    if (sheetState.isVisible) sheetState.hide()
                    else sheetState.show()
                }
            },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = UiJobsR.drawable.ic_job_search),
                    contentDescription = "Search roles"
                )
                Spacer(modifier = Modifier.size(dimensionResource(id = BaseR.dimen.spacing_4x)))
                Text(text = stringResource(id = UiJobsR.string.label_search_role), style = MaterialTheme.typography.subtitle1)
            }
        },
        enabled = false
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CityField(
    cityText: String,
    sheetState: ModalBottomSheetState,
    setCityText: (String) -> Unit,
    setItemSelected: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()

    TextField(
        value = cityText, onValueChange = { setCityText(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = BaseR.dimen.spacing_base))
            .neu(
                NeuAttrs(
                    lightShadowColor = AppColors.lightShadow(),
                    darkShadowColor = AppColors.darkShadow(),
                    shadowElevation = dimensionResource(id = BaseR.dimen.spacing_2x),
                    lightSource = LightSource.LEFT_TOP,
                    shape = Pressed(RoundedCorner(dimensionResource(id = BaseR.dimen.spacing_3x))),
                )
            )
            .clickable {
                coroutineScope.launch {
                    setItemSelected.invoke()
                    if (sheetState.isVisible) sheetState.hide()
                    else sheetState.show()
                }
            },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = UiJobsR.drawable.ic_job_search),
                    contentDescription = "Search City"
                )
                Spacer(modifier = Modifier.size(dimensionResource(id = BaseR.dimen.spacing_4x)))
                Text(text = stringResource(id = UiJobsR.string.label_search_city), style = MaterialTheme.typography.subtitle1)
            }
        },
        enabled = false
    )
}



