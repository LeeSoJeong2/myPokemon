package com.kt.startkit.ui.res

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.kt.startkit.R

object IconResId {
    const val home = R.drawable.home_24
    const val setting = R.drawable.settings_24
    val berry = Icons.Rounded.Favorite

    val favorite = Icons.Outlined.FavoriteBorder

    val Add = Icons.Rounded.Add
    val ArrowBack = Icons.Rounded.ArrowBack
    val Search = Icons.Rounded.Search
}

sealed class IconRes {
    data class ImageVectorIcon(val imageVector: ImageVector) : IconRes()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : IconRes()
}