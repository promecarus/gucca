@file:Suppress("unused", "RedundantSuppression")

package com.promecarus.core.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.promecarus.core.domain.model.User
import com.promecarus.core.domain.model.UserDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailBottomSheet(
    userDetail: UserDetail,
    showBottomSheet: Boolean,
    onShowBottomSheetChange: (Boolean) -> Unit,
    selectedTabIndex: Int,
    onSelectedTabIndexChange: (Int) -> Unit,
    followers: List<User>,
    following: List<User>,
    favoriteIds: List<Int>,
    getUserDetail: (String) -> Unit,
    getFollowers: (String) -> Unit,
    getFollowing: (String) -> Unit,
    insert: (User) -> Unit,
    delete: (User) -> Unit,
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onShowBottomSheetChange(false)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(userDetail.avatar_url).crossfade(true).build(),
                    contentDescription = "${userDetail.login}'s user avatar",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .size(size = 112.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Fit,
                )

                if (userDetail.name != null) {
                    Text(
                        text = userDetail.name,
                        modifier = Modifier.padding(bottom = 4.dp),
                    )

                    Text(
                        text = userDetail.login,
                        modifier = Modifier.padding(bottom = 16.dp),
                    )
                } else Text(
                    text = userDetail.login,
                    modifier = Modifier.padding(bottom = 16.dp),
                )

                FollowingFollowersText(userDetail = userDetail)

                RepoGistText(userDetail = userDetail)

                TabRow(selectedTabIndex = selectedTabIndex) {
                    listOf(
                        "Followers",
                        "Following",
                    ).forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = {
                                onSelectedTabIndexChange(index)
                            },
                            text = {
                                Text(text = title)
                            },
                        )
                    }
                }

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    when {
                        selectedTabIndex == 0 && followers.isEmpty() -> item {
                            Text(
                                text = "No data",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                textAlign = TextAlign.Center,
                            )
                        }

                        selectedTabIndex == 1 && following.isEmpty() -> item {
                            Text(
                                text = "No data",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    items(
                        items = if (selectedTabIndex == 0) followers else following,
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
                            insert = insert,
                            delete = delete,
                        )
                    }
                }
            }
        }
    }
}
