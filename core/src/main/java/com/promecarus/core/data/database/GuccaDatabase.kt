package com.promecarus.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.promecarus.core.data.database.dao.FavoriteDao
import com.promecarus.core.data.database.dao.HistoryDao
import com.promecarus.core.data.database.entity.FavoriteEntity
import com.promecarus.core.data.database.entity.HistoryEntity

@Database(
    entities = [
        FavoriteEntity::class,
        HistoryEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class GuccaDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun historyDao(): HistoryDao
}
