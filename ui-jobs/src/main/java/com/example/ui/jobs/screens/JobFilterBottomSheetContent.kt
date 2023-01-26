package com.example.ui.jobs.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

//roleContent
@Composable
fun RoleContentBottomSheet(
    roleText: String,
    sheetStateHide: () -> Unit,
    onChangeRoleText: (String) -> Unit,
    roleList: List<String>
) {
    val allCityList = mutableListOf("")
    allCityList.addAll(roleList)
    val (selectedOption: String, onOptionSelected: (String) -> Unit) = remember {
        mutableStateOf(
            allCityList[allCityList.indexOf(roleText)]
        )
    }
    onChangeRoleText(selectedOption)
    Column(
        modifier = Modifier
            .selectableGroup()
            .padding(32.dp)
    ) {
        Text(
            text = "Role",
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
        ) {
            items(allCityList) { item ->
                SelectOptionsCheckoutRole(
                    text = item,
                    isSelectedOption = selectedOption == item,
                    onSelectOption = onOptionSelected,
                    sheetStateHide
                )
            }
        }
    }
}

@Composable
fun SelectOptionsCheckoutRole(
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
            imageVector = checkboxResourceRole(isSelected = isSelectedOption),
            contentDescription = "Checkbox",
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            tint = MaterialTheme.colors.primary
        )
        Text(text, style = MaterialTheme.typography.subtitle1)

        Spacer(modifier = Modifier.height(16.dp))

    }
}

fun checkboxResourceRole(isSelected: Boolean): ImageVector {
    return if (isSelected) {
        Icons.Default.RadioButtonChecked
    } else {
        Icons.Default.RadioButtonUnchecked
    }
}

//cityContent
@Composable
fun CityContentBottomSheet(
    cityText: String,
    sheetStateHide: () -> Unit,
    onChangeLocationText: (String) -> Unit,
    locationList: List<String> = listOf()
) {
    val allLocationList = mutableListOf("")
    allLocationList.addAll(locationList)
    val (selectedOption: String, onOptionSelected: (String) -> Unit) = remember {
        mutableStateOf(
            allLocationList[allLocationList.indexOf(if (locationList.isNotEmpty())cityText else "")]
        )
    }
    onChangeLocationText(selectedOption)
    Column(
        Modifier
            .selectableGroup()
            .padding(32.dp)
    ) {
        Text(
            text = "City",
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(16.dp))
        allLocationList.forEach { text ->
            SelectOptionsCheckoutCity(
                text = text ?: "",
                isSelectedOption = selectedOption == text,
                onSelectOption = onOptionSelected,
                sheetStateHide
            )
        }
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
            imageVector = checkboxResourceCity(isSelected = isSelectedOption),
            contentDescription = "Checkbox",
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            tint = MaterialTheme.colors.primary
        )
        Text(text, style = MaterialTheme.typography.subtitle1)

        Spacer(modifier = Modifier.height(16.dp))

    }
}

fun checkboxResourceCity(isSelected: Boolean): ImageVector {
    return if (isSelected) {
        Icons.Default.RadioButtonChecked
    } else {
        Icons.Default.RadioButtonUnchecked
    }
}



