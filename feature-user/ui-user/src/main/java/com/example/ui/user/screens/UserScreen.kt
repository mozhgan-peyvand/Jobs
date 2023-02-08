package com.example.ui.user.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.base.models.UserInfoEntity
import com.example.base.util.Loading
import com.example.base.util.Success
import com.example.base.util.Uninitialized
import com.example.common.ui.view.theme.h3Primary
import com.example.ui.user.models.UserScreenState
import com.example.base.R as BaseR
import com.example.ui.user.R as UiUserR

@Composable
fun UserScreen(viewModel: UserViewModel) {

    val viewState by viewModel.stateFlow.collectAsState(initial = UserScreenState())
    var userInfoList = listOf<UserInfoEntity>()
    when(viewState.userInfoList){
        is Success -> {
            userInfoList = viewState.userInfoList.invoke() ?: emptyList()
        }
        is Loading -> {
            CircularProgressIndicator()
        }
    }
    UserScreen(userInfoList)
}

@Composable
fun UserScreen(userInfoList : List<UserInfoEntity>) {

    val uriHandler = LocalUriHandler.current
    Box(
        modifier = Modifier.fillMaxSize(),

        ) {
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(UiUserR.drawable.image_profile),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = BaseR.dimen.spacing_32x))
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(id = UiUserR.string.label_first_and_last_name),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = dimensionResource(id = BaseR.dimen.spacing_4x)),
                style = MaterialTheme.typography.h3Primary()
            )
            Text(
                text = stringResource(id = UiUserR.string.msg_role_info),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = UiUserR.string.msg_birthday_info),
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Text(
                text = stringResource(id = UiUserR.string.msg_location),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            LazyColumn(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                items(userInfoList) { item ->
                    val linkItemUserInfo = item.link?.let {
                        stringResource(id = it)
                    }

                    UserProfileSocialNetworkItem(item = item) {
                        linkItemUserInfo?.let {
                            uriHandler.openUri(it)
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(horizontal = dimensionResource(id = BaseR.dimen.spacing_4x)),
                        color = MaterialTheme.colors.onBackground,
                        thickness = 1.dp
                    )
                }
            }
        }

    }
}
