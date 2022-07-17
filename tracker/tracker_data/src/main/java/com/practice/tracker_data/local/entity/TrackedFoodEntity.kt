package com.practice.tracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackedFoodEntity(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val carbs: Int,
    val protein: Int,
    val imageUrl: String?,
    val type: String,
    val amount: Int,
    val dayOfMonth: Int,
    val Month: Int,
    val year: Int,
    val calories: Int,
)