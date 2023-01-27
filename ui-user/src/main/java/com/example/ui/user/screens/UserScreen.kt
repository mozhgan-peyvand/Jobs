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
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.common.ui.view.theme.captionOnPrimary
import com.example.common.ui.view.theme.h3Primary

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
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
            )
            Text(
                text = "Mozhgan Peivandian",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                style = MaterialTheme.typography.h3Primary()
            )
            Text(
                text = "I`m Android Developer",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "My Birthday : 1999 July 18",
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Text(text = "Live in Mashhad", modifier = Modifier.align(Alignment.CenterHorizontally))
            val list = listOf<UserNetworkEntity>(
                UserNetworkEntity("Github", "https://github.com/mozhgan-peyvand"),
                UserNetworkEntity("Linkdin", "https://www.linkedin.com/in/mozhganpeyvand/"),
                UserNetworkEntity("Instagram", "https://www.instagram.com/mozhgan.peyvand.sh/")
            )
            LazyColumn(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                items(list) { item ->
                    UserProfileSocialNetworkItem(item = item) {
                        item.link.let {
                            uriHandler.openUri(it)
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        color = MaterialTheme.colors.onBackground,
                        thickness = 1.dp
                    )
                }
            }
        }

    }
}

// on below line we are creating a function to get bitmap
// from image and passing params as context and an int for drawable.
private fun getBitmapFromImage(context: Context, drawable: Int): Bitmap {

    // on below line we are getting drawable
    val db = ContextCompat.getDrawable(context, drawable)

    // in below line we are creating our bitmap and initializing it.
    val bit = Bitmap.createBitmap(
        db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
    )

    // on below line we are
    // creating a variable for canvas.
    val canvas = Canvas(bit)

    // on below line we are setting bounds for our bitmap.
    db.setBounds(0, 0, canvas.width, canvas.height)

    // on below line we are simply
    // calling draw to draw our canvas.
    db.draw(canvas)

    // on below line we are
    // returning our bitmap.
    return bit
}