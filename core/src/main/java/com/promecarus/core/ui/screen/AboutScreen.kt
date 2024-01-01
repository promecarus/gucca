package com.promecarus.core.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.promecarus.core.domain.model.UserDetail
import com.promecarus.core.ui.component.FollowingFollowersText
import com.promecarus.core.ui.component.RepoGistText

@Composable
fun AboutScreen(
    userDetail: UserDetail,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(userDetail.avatar_url)
                .crossfade(true).build(),
            contentDescription = "${userDetail.login}'s user avatar",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(size = 112.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Fit,
        )

        Text(
            text = userDetail.name ?: "",
            modifier = Modifier.padding(bottom = 4.dp),
        )

        Text(
            text = userDetail.login,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        Text(
            text = userDetail.bio ?: "",
            modifier = Modifier.padding(bottom = 16.dp),
        )

        Text(
            text = "${userDetail.location} · ${userDetail.company}",
            modifier = Modifier.padding(bottom = 16.dp),
        )

        FollowingFollowersText(userDetail = userDetail)

        RepoGistText(userDetail = userDetail)
    }
}
