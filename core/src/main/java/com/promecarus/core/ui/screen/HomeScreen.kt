@file:Suppress("unused", "RedundantSuppression")

package com.promecarus.core.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ListItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.promecarus.core.data.database.entity.HistoryEntity
import com.promecarus.core.data.remote.ApiResponse
import com.promecarus.core.domain.model.Search
import com.promecarus.core.domain.model.Setting
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.model.UserDetail
import com.promecarus.core.ui.component.UserDetailBottomSheet
import com.promecarus.core.ui.component.UserItem

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
fun HomeScreen(
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    openSettingDialog: Boolean,
    onOpenSettingDialogChange: (Boolean) -> Unit,
    setting: Setting,
    onSettingChange: (Setting) -> Unit,
    onSettingTempChange: (Setting) -> Unit,
    insertHistory: (String) -> Unit,
    histories: List<HistoryEntity>,
    deleteHistory: (HistoryEntity) -> Unit,
    apiResponse: ApiResponse<Search>,
    clearQuery: () -> Unit,
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
    insertFavorite: (User) -> Unit,
    deleteFavorite: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        DockedSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = {
                onSearch(it)
                onActiveChange(false)
                insertHistory(it)
            },
            active = active,
            onActiveChange = onActiveChange,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(top = 16.dp),
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                )
            },
            trailingIcon = {
                Row {
                    when {
                        query.isNotEmpty() -> IconButton(onClick = clearQuery) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = "Clear",
                            )
                        }

                        active -> IconButton(
                            onClick = {
                                onActiveChange(false)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = "Close",
                            )
                        }
                    }

                    IconButton(
                        onClick = {
                            onOpenSettingDialogChange(true)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Settings",
                        )
                    }
                }
            },
        ) {
            LazyColumn {
                items(
                    items = histories,
                    key = {
                        it.timestamp
                    },
                ) {
                    ListItem(
                        modifier = Modifier.clickable {
                            onQueryChange(it.query)
                            onSearch(it.query)
                            onActiveChange(false)
                            insertHistory(it.query)
                        },
                        trailing = {
                            IconButton(
                                onClick = {
                                    deleteHistory(it)
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "Delete",
                                )
                            }
                        },
                        text = {
                            Text(text = it.query)
                        },
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = if (apiResponse is ApiResponse.Success && apiResponse.data.items.isNotEmpty()) 72.dp else 0.dp),
            verticalArrangement = if (apiResponse is ApiResponse.Success && apiResponse.data.items.isNotEmpty()) Arrangement.Top else Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (apiResponse) {
                is ApiResponse.Loading -> item {
                    Text(text = "Loading")
                }

                is ApiResponse.Success -> if (apiResponse.data.items.isEmpty()) item {
                    Text(text = "No data")
                } else items(
                    items = apiResponse.data.items,
                    key = { user ->
                        user.id
                    },
                ) { user ->
                    UserItem(
                        user = user,
                        favoriteIds = favoriteIds,
                        getUserDetail = getUserDetail,
                        getFollowers = getFollowers,
                        getFollowing = getFollowing,
                        onShowBottomSheetChange = onShowBottomSheetChange,
                        insert = insertFavorite,
                        delete = deleteFavorite,
                    )
                }

                is ApiResponse.Error -> item {
                    Text(text = apiResponse.message)
                }
            }
        }

        if (openSettingDialog) {
            AlertDialog(
                title = {
                    Text(text = "Setting")
                },
                text = {
                    Column {
                        Text(text = "Search: ${setting.search}")

                        Slider(
                            value = setting.search.toFloat(),
                            onValueChange = {
                                onSettingTempChange(setting.copy(search = it.toInt()))
                            },
                            valueRange = 1f..100f,
                            onValueChangeFinished = {
                                onSettingChange(setting)
                            },
                        )

                        Text(text = "Followers: ${setting.followers}")

                        Slider(
                            value = setting.followers.toFloat(),
                            onValueChange = {
                                onSettingTempChange(setting.copy(followers = it.toInt()))
                            },
                            valueRange = 1f..100f,
                            onValueChangeFinished = {
                                onSettingChange(setting)
                            },
                        )

                        Text(text = "Following: ${setting.following}")

                        Slider(
                            value = setting.following.toFloat(),
                            onValueChange = {
                                onSettingTempChange(setting.copy(following = it.toInt()))
                            },
                            valueRange = 1f..100f,
                            onValueChangeFinished = {
                                onSettingChange(setting)
                            },
                        )
                    }
                },
                onDismissRequest = {
                    onOpenSettingDialogChange(false)
                },
                confirmButton = {
                    TextButton(onClick = {
                        onOpenSettingDialogChange(false)
                    }) {
                        Text("Confirm")
                    }
                },
            )
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
            insert = insertFavorite,
            delete = deleteFavorite,
        )
    }
}
