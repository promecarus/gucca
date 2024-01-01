package com.promecarus.core.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.promecarus.core.R
import com.promecarus.core.domain.model.UserDetail

@Composable
fun FollowingFollowersText(
    userDetail: UserDetail,
    modifier: Modifier = Modifier,
) {
    Text(
        text = pluralStringResource(
            id = R.plurals.follow,
            count = userDetail.followers,
            userDetail.followers,
            userDetail.following
        ),
        modifier = modifier.padding(bottom = 4.dp),
    )
}
