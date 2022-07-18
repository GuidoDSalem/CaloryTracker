package com.practice.tracker_domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class TrackedFood(
    val name: String,
    val carbs: Int,
    val proteins:Int,
    val fat: Int,
    val imageUrl: String?,
    val mealType: MealType,
    val amount: Int,
    val date: LocalDate,
    val calories: Int,
    val id: Int? = null
)
