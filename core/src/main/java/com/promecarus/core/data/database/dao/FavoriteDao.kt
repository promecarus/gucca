package com.promecarus.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.promecarus.core.data.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM FavoriteEntity ORDER BY id ASC")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Query("SELECT id FROM FavoriteEntity")
    fun getAllId(): Flow<List<Int>>

    @Delete
    suspend fun delete(favoriteEntity: FavoriteEntity)
}
