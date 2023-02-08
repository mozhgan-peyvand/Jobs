package com.example.ui.user.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.base.models.UserInfoEntity
import com.example.base.R as BaseR
import com.example.ui.user.R as UiUserR

@Composable
fun UserProfileSocialNetworkItem(
    item: UserInfoEntity,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
            .padding(dimensionResource(id = BaseR.dimen.spacing_4x)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(dimensionResource(id = BaseR.dimen.spacing_8x)),
            painter = painterResource(id = item.image),
            contentDescription = ""
        )
        Spacer(Modifier.weight(1f))
        item.title.let {
            Text(
                text = stringResource(id = it),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.secondary
            )
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(id = UiUserR.drawable.ic_arrow_right),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(id = BaseR.dimen.spacing_3x)),
                tint = MaterialTheme.colors.secondary,
            )
        }
    }
}
