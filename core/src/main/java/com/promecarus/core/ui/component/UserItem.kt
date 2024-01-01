@file:Suppress("unused", "RedundantSuppression")

package com.promecarus.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.promecarus.core.domain.model.User

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserItem(
    user: User,
    favoriteIds: List<Int>,
    getUserDetail: (String) -> Unit,
    getFollowers: (String) -> Unit,
    getFollowing: (String) -> Unit,
    insert: (User) -> Unit,
    delete: (User) -> Unit,
    onShowBottomSheetChange: (Boolean) -> Unit = {},
) {
    ListItem(
        modifier = Modifier.clickable {
            getUserDetail(user.login)
            getFollowers(user.login)
            getFollowing(user.login)
            onShowBottomSheetChange(true)
        },
        icon = {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data = user.avatar_url).crossfade(enable = true).build(),
                contentDescription = "${user.login}'s avatar",
                modifier = Modifier
                    .size(size = 56.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Fit,
            )
        },
        secondaryText = {
            Text(text = "ID: ${user.id} | Type: ${user.type}")
        },
        trailing = {
            IconButton(
                onClick = {
                    if (favoriteIds.contains(user.id)) delete(user) else insert(user)
                },
            ) {
                Icon(
                    imageVector = if (favoriteIds.contains(user.id)) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                )
            }
        },
        text = {
            Text(text = user.login)
        },
    )
}
