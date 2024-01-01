package com.promecarus.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.promecarus.core.data.database.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM HistoryEntity ORDER BY timestamp DESC")
    fun getAll(): Flow<List<HistoryEntity>>

    @Delete
    suspend fun delete(historyEntity: HistoryEntity)
}
