package com.example.ui.jobs.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun RoleContentBottomSheet(roleText: String, sheetStateHide: () -> Unit, param: (String) -> Unit) {
    val radioOptions1 = listOf("OptionA", "react", "OptionC")

    val (selectedOption: String, onOptionSelected: (String) -> Unit) = remember {
        mutableStateOf(
            radioOptions1[radioOptions1.indexOf(roleText)]
        )
    }
    param(selectedOption)
    Column(
        Modifier
            .selectableGroup()
            .padding(32.dp)
    ) {
        Text(
            text = "Role",
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(16.dp))
        radioOptions1.forEach { text ->
            SelectOptionsCheckoutRole(
                text = text,
                isSelectedOption = selectedOption == text,
                onSelectOption = onOptionSelected,
                sheetStateHide
            )
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
    function: (String) -> Unit
) {
    val radioOptions = listOf("OptionA", "london", "OptionC")

    val (selectedOption: String, onOptionSelected: (String) -> Unit) = remember {
        mutableStateOf(
            radioOptions[radioOptions.indexOf(cityText)]
        )
    }
    function(selectedOption)
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



