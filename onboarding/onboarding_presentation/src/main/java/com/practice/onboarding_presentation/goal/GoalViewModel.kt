package com.practice.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.core.domain.model.ActivityLevel
import com.practice.core.domain.model.Gender
import com.practice.core.domain.model.GoalType
import com.practice.core.domain.preferences.Preferences
import com.practice.core.navigation.Route
import com.practice.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {
    var selectedGoal by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalTypeSelect(goal: GoalType){
        selectedGoal = goal
    }


    fun onNextClick(){
        viewModelScope.launch{
            preferences.saveGoalType(selectedGoal)
            _uiEvent.send(UIEvent.Navigate(Route.NUTRIENT_GOAL))
        }

    }
}