package com.promecarus.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey val query: String,
    val timestamp: Long,
)
