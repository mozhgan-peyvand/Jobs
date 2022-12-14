package com.example.builder.ui

import android.annotation.SuppressLint
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
import com.example.base.shape.LightSource
import com.example.base.shape.Pressed
import com.example.base.shape.RoundedCorner
import com.example.base.shape.neu
import com.example.builder.R
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout(onCloseDrawer: () -> Unit) {
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

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { BottomSheet() },
        modifier = Modifier.fillMaxSize()
    ) {

        var roleText by remember {
            mutableStateOf("")
        }
        var cityText by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(236, 234, 235))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose your option",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
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
                    .height(50.dp)
                    .clickable {
                        coroutineScope.launch {
                            if (sheetState.isVisible) sheetState.hide()
                            else sheetState.show()
                        }
                    },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(236, 234, 235),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                value = roleText,
                onValueChange = { roleText = it },
                placeholder = { Text(text = "Enter role") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_job_search),
                        contentDescription = "",
                        tint = Color(32, 184, 184, 255)
                    )
                },
                enabled = false
            )
            Spacer(modifier = Modifier.height(32.dp))

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
                    .height(50.dp)
                    .clickable {
                        coroutineScope.launch {
                            if (sheetState.isVisible) sheetState.hide()
                            else sheetState.show()
                        }
                    },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(236, 234, 235),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent

                ),
                value = cityText,
                onValueChange = { cityText = it },
                placeholder = { Text(text = "Enter city") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_job_search),
                        contentDescription = "",
                        tint = Color(32, 184, 184, 255)
                    )
                },
                enabled = false
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier,
                onClick = {
                    onCloseDrawer.invoke()
                },
                shape = RoundedCornerShape(CornerSize(24.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(32, 184, 184, 255)
                ),
                elevation = ButtonDefaults.elevation(8.dp)
            ) {
                Text(text = "Filter", style = MaterialTheme.typography.h6)
            }
        }
    }
}

@Composable
fun BottomSheet() {
    Column(
        modifier = Modifier.padding(32.dp)
    ) {
        Text(
            text = "Bottom sheet",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Click outside the bottom sheet to hide it",
            style = MaterialTheme.typography.body1
        )
    }
}

