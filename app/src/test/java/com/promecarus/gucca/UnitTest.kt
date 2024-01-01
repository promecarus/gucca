package com.promecarus.gucca

import com.promecarus.core.data.database.entity.FavoriteEntity
import com.promecarus.core.util.Mapper.toFavoriteEntity
import com.promecarus.core.util.Mapper.toUser
import org.junit.Assert
import org.junit.Test

class UnitTest {
    @Test
    fun `FavoriteEntity mapped to User is correct`() {
        val expected = FavoriteEntity(
            login = "login",
            id = 1,
            avatarUrl = "avatarUrl",
            type = "type",
        )

        val actual = expected.toUser()

        Assert.assertEquals(expected.login, actual.login)
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.avatarUrl, actual.avatar_url)
        Assert.assertEquals(expected.type, actual.type)
    }

    @Test
    fun `User mapped to FavoriteEntity is correct`() {
        val expected = com.promecarus.core.domain.model.User(
            login = "login",
            id = 1,
            avatar_url = "avatarUrl",
            type = "type",
        )

        val actual = expected.toFavoriteEntity()

        Assert.assertEquals(expected.login, actual.login)
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.avatar_url, actual.avatarUrl)
        Assert.assertEquals(expected.type, actual.type)
    }
}
