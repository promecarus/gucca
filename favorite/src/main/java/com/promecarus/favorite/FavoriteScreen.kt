@file:Suppress("unused")

package com.promecarus.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.promecarus.core.data.database.entity.FavoriteEntity
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.model.UserDetail
import com.promecarus.core.ui.component.UserDetailBottomSheet
import com.promecarus.core.util.Mapper.toUser

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteScreen(
    favorites: List<FavoriteEntity>,
    showBottomSheet: Boolean,
    onShowBottomSheetChange: (Boolean) -> Unit,
    userDetail: UserDetail,
    getUserDetail: (String) -> Unit,
    selectedTabIndex: Int,
    onSelectedTabIndexChange: (Int) -> Unit,
    followers: List<User>,
    getFollowers: (String) -> Unit,
    following: List<User>,
    getFollowing: (String) -> Unit,
    favoriteIds: List<Int>,
    insert: (User) -> Unit,
    delete: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = if (favorites.isEmpty()) Arrangement.Center else Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (favorites.isEmpty()) {
                item {
                    Text(text = "No data")
                }
            } else items(
                items = favorites,
                key = {
                    it.id
                },
            ) {
                ListItem(
                    modifier = Modifier.clickable {
                        getUserDetail(it.login)
                        getFollowers(it.login)
                        getFollowing(it.login)
                        onShowBottomSheetChange(true)
                    },
                    icon = {
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(data = it.avatarUrl).crossfade(enable = true).build(),
                            contentDescription = "${it.login}'s avatar",
                            modifier = Modifier
                                .size(size = 56.dp)
                                .clip(shape = CircleShape),
                            contentScale = ContentScale.Fit,
                        )
                    },
                    secondaryText = {
                        Text(text = "ID: ${it.id} | Type: ${it.type}")
                    },
                    trailing = {
                        IconButton(
                            onClick = {
                                delete(it.toUser())
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete",
                            )
                        }
                    },
                    text = {
                        Text(text = it.login)
                    },
                )
            }
        }

        UserDetailBottomSheet(
            userDetail = userDetail,
            showBottomSheet = showBottomSheet,
            onShowBottomSheetChange = onShowBottomSheetChange,
            selectedTabIndex = selectedTabIndex,
            onSelectedTabIndexChange = onSelectedTabIndexChange,
            followers = followers,
            following = following,
            favoriteIds = favoriteIds,
            getUserDetail = getUserDetail,
            getFollowers = getFollowers,
            getFollowing = getFollowing,
            insert = insert,
            delete = delete,
        )
    }
}
