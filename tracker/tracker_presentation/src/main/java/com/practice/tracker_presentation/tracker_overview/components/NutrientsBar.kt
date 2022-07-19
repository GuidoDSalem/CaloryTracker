package com.practice.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames.remember
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.practice.core_ui.CarbColor
import com.practice.core_ui.FatColor
import com.practice.core_ui.ProteinColor

@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.background
    val caloriesExceedColor = MaterialTheme.colors.error
    val carbsWidthRatio = remember{
        Animatable(0f)
    }
    val proteinWidthRatio = remember{
        Animatable(0f)
    }
    val fatWidthRatio = remember{
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs){
        carbsWidthRatio.animateTo(
            targetValue = ((carbs * 4f)/calorieGoal)
        )
    }
    LaunchedEffect(key1 = protein){
        proteinWidthRatio.animateTo(
            targetValue = ((carbs * 4f)/calorieGoal)
        )
    }
    LaunchedEffect(key1 = fat){
        fatWidthRatio.animateTo(
            targetValue = ((carbs * 9f)/calorieGoal)
        )
    }

    Canvas(modifier = modifier){
        if(calories <= calorieGoal){
            val carbsWith = carbsWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width

            drawRoundRect(color=background,size = size, cornerRadius = CornerRadius(100f))

            drawRoundRect(color= FatColor,
                size = Size(
                    width = carbsWith+proteinWidth+fatWidth,
                    height = size.height),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(color= ProteinColor,
                size = Size(
                    width = carbsWith+proteinWidth,
                    height = size.height),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(color= CarbColor,
                size = Size(
                    width = carbsWith,
                    height = size.height),
                cornerRadius = CornerRadius(100f)
            )
        }
        else{
            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }

}
