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
fun RepoGistText(
    userDetail: UserDetail,
    modifier: Modifier = Modifier,
) {
    Text(
        text = pluralStringResource(
            id = R.plurals.repo,
            count = userDetail.public_repos,
            userDetail.public_repos,
        ) + " · " + pluralStringResource(
            id = R.plurals.gist,
            count = userDetail.public_gists,
            userDetail.public_gists,
        ),
        modifier = modifier.padding(bottom = 16.dp),
    )
}
