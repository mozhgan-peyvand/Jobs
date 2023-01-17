package com.example.ui.jobs.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.base.AppColors
import com.example.base.R
import com.example.base.shape.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterJobs(
    onCloseDrawer: () -> Unit,
    param: (List<String>) -> Boolean,
    clear: () -> Unit
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
        mutableStateOf(0)
    }
    var cityText by remember {
        mutableStateOf("OptionA")
    }
    var roleText by remember {
        mutableStateOf("OptionA")
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            if (selectedItem == 0)
                RoleContentBottomSheet(
                roleText,
                { coroutineScope.launch { sheetState.hide() } },
                { roleText = it })
            else
                CityContentBottomSheet(
                    cityText,
                    { coroutineScope.launch { sheetState.hide() } },
                    { cityText = it }
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
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                value = roleText, onValueChange = { roleText = it },
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
                    ).clickable {
                        coroutineScope.launch {
                            selectedItem = 0
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
                        Icon(painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_search), contentDescription = "Search")
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = "Enter Role", style = MaterialTheme.typography.subtitle1)
                    }
                },
                enabled = false
            )
            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = cityText, onValueChange = { cityText = it },
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
                    ).clickable {
                        coroutineScope.launch {
                            selectedItem = 1
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
                        Icon(painter = painterResource(id = com.example.ui.jobs.R.drawable.ic_job_search), contentDescription = "Search")
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = "Enter City",style = MaterialTheme.typography.subtitle1)
                    }
                },
                enabled = false
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier,
                onClick = {
                    clear.invoke()
                    param(listOf(roleText, cityText))
                    onCloseDrawer.invoke()
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

@Composable
fun CityContentBottomSheet(cityText: String,sheetStateHide: () -> Unit, function: (String) -> Unit) {
    val radioOptions = listOf("OptionA", "OptionB", "OptionC")

    val (selectedOption: String, onOptionSelected: (String) -> Unit) = remember {
        mutableStateOf(
            radioOptions[radioOptions.indexOf(cityText)]
        )
    }
    function(selectedOption)
    Column(
        Modifier
            .selectableGroup()
            .padding(32.dp)) {
        Text(
            text = "City",
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(16.dp))
        radioOptions.forEach { text ->
            SelectOptionsCheckoutCity(
                text = text,
                isSelectedOption = selectedOption == text,
                onSelectOption = onOptionSelected,
                sheetStateHide
            )
        }
    }
}

@Composable
fun RoleContentBottomSheet(roleText: String, sheetStateHide: () -> Unit, param: (String) -> Unit) {
    val radioOptions1 = listOf("OptionA", "OptionB", "OptionC")

    val (selectedOption: String, onOptionSelected: (String) -> Unit) = remember {
        mutableStateOf(
            radioOptions1[radioOptions1.indexOf(roleText)]
        )
    }
    param(selectedOption)
    Column(
        Modifier
            .selectableGroup()
            .padding(32.dp)) {
        Text(
            text = "Role",
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(16.dp))
        radioOptions1.forEach { text ->
            SelectOptionsCheckout(
                text = text,
                isSelectedOption = selectedOption == text,
                onSelectOption = onOptionSelected,
                sheetStateHide
            )
        }
    }
}

@Composable
fun checkboxResource1(isSelected: Boolean): ImageVector {
    return if (isSelected) {
        Icons.Default.RadioButtonChecked
    } else {
        Icons.Default.RadioButtonUnchecked
    }
}

@Composable
fun CheckboxResource(isSelected: Boolean): ImageVector {
    return if (isSelected) {
        Icons.Default.RadioButtonChecked
    } else {
        Icons.Default.RadioButtonUnchecked
    }
}


@Composable
fun SelectOptionsCheckoutCity(
    text: String,
    isSelectedOption: Boolean,
    onSelectOption: (String) -> Unit,
    sheetStateHide: () -> Unit
) {

    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable {
            onSelectOption(text)
            sheetStateHide.invoke()
        }) {
        Icon(
            imageVector = CheckboxResource(isSelected = isSelectedOption),
            contentDescription = "Checkbox",
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            tint = MaterialTheme.colors.primary
        )
        Text(text, style = MaterialTheme.typography.subtitle1)

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun SelectOptionsCheckout(
    text: String,
    isSelectedOption: Boolean,
    onSelectOption: (String) -> Unit,
    sheetStateHide: () -> Unit
) {

    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable {
            onSelectOption(text)
            sheetStateHide.invoke()
        }) {
        Icon(
            imageVector = checkboxResource1(isSelected = isSelectedOption),
            contentDescription = "Checkbox",
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            tint = MaterialTheme.colors.primary
        )
        Text(text, style = MaterialTheme.typography.subtitle1)

        Spacer(modifier = Modifier.height(16.dp))

    }
}

