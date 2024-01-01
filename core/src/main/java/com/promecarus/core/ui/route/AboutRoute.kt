@file:Suppress("SpellCheckingInspection")

package com.promecarus.core.ui.route

import androidx.compose.runtime.Composable
import com.promecarus.core.domain.model.UserDetail
import com.promecarus.core.ui.screen.AboutScreen

@Composable
fun AboutRoute() {
    val promecarusUserDetail = UserDetail(
        login = "promecarus",
        id = 34930290,
        avatar_url = "https://avatars.githubusercontent.com/u/34930290?v=4",
        type = "User",
        name = "Muhammad Haikal Al Rasyid",
        company = "Politeknik Negeri Jakarta",
        location = "Indonesia",
        bio = "Add a bio",
        public_repos = 75,
        public_gists = 12,
        followers = 36,
        following = 59,
    )

    AboutScreen(userDetail = promecarusUserDetail)
}
