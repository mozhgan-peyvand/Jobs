package com.example.ui.jobs.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.common.ui.view.theme.AppColors
import com.example.base.util.shape.*
import com.example.ui.jobs.models.JobScreenState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterJobs(
    onCloseFilterJobsDrawer: () -> Unit,
    addFilterResultJob: (List<String>) -> Boolean,
    clearFilterResultJob: () -> Unit,
    filterJobList: (String?, String?) -> Unit,
    viewState: JobScreenState,
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
        mutableStateOf("mozhgan")
    }
    var roleText by remember {
        mutableStateOf("Legal Counsel")
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            if (selectedItem == 0)
                RoleContentBottomSheet(
                    roleText,
                    { coroutineScope.launch { sheetState.hide() } },
                    { roleText = it },
                    roleList = viewState.allRoleList
                    )
            else
                CityContentBottomSheet(
                    cityText,
                    { coroutineScope.launch { sheetState.hide() } },
                    { cityText = it },
                    locationList = viewState.allLocationList
                )
        },
        modifier = Modifier.fillMaxSize(),
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose your option",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            RoleField(roleText, sheetState,{ roleText = it }) { selectedItem = 0 }

            Spacer(modifier = Modifier.height(32.dp))

            CityField(cityText, sheetState,{ cityText = it }) { selectedItem = 1 }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier,
                onClick = {
                    filterJobList.invoke(roleText , cityText)
                    clearFilterResultJob.invoke()
                    addFilterResultJob(listOf(roleText, cityText))
                    onCloseFilterJobsDrawer.invoke()
                },
                shape = RoundedCornerShape(CornerSize(24.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                ),
                elevation = ButtonDefaults.elevation(8.dp)
            ) {
                Text(text = "Filter", style = MaterialTheme.typography.h3)
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
            .padding(4.dp)
            .neu(
                NeuAttrs(
                    lightShadowColor = AppColors.lightShadow(),
                    darkShadowColor = AppColors.darkShadow(),
                    shadowElevation = 6.dp,
                    lightSource = LightSource.LEFT_TOP,
                    shape = Pressed(RoundedCorner(12.dp)),
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
                    painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_search),
                    contentDescription = "Search"
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "Enter Role", style = MaterialTheme.typography.subtitle1)
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
            .padding(4.dp)
            .neu(
                NeuAttrs(
                    lightShadowColor = AppColors.lightShadow(),
                    darkShadowColor = AppColors.darkShadow(),
                    shadowElevation = 6.dp,
                    lightSource = LightSource.LEFT_TOP,
                    shape = Pressed(RoundedCorner(12.dp)),
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
                    painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_search),
                    contentDescription = "Search"
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "Enter City", style = MaterialTheme.typography.subtitle1)
            }
        },
        enabled = false
    )
}



