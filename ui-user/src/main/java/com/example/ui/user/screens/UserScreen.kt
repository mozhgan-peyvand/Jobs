package com.example.ui.user.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.common.ui.view.theme.captionOnPrimary
import com.example.common.ui.view.theme.h3Primary
import com.example.base.R as BaseR
import com.example.ui.user.R as UiUserR
@Composable
fun UserScreen() {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    Box(
        modifier = Modifier.fillMaxSize(),

        ) {
        val bitmap =
            getBitmapFromImage(context, com.example.common.ui.view.R.mipmap.ic_launcher_profile)

        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
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
            val list = listOf(
                UserNetworkEntity(
                    stringResource(id = UiUserR.string.github_title),
                    stringResource(id = UiUserR.string.link_github)
                ),
                UserNetworkEntity(
                    stringResource(id = UiUserR.string.linkedin_title),
                    stringResource(id = UiUserR.string.link_linkedin)
                ),
                UserNetworkEntity(
                    stringResource(id = UiUserR.string.instagram_title),
                    stringResource(id = UiUserR.string.link_instagram)
                )
            )
            LazyColumn(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                items(list) { item ->
                    UserProfileSocialNetworkItem(item = item) {
                        item.link.let {
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

private fun getBitmapFromImage(context: Context, drawable: Int): Bitmap {
    val db = ContextCompat.getDrawable(context, drawable)
    val bit = Bitmap.createBitmap(
        db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bit)
    db.setBounds(0, 0, canvas.width, canvas.height)
    db.draw(canvas)
    return bit
}