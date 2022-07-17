package com.practice.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.core.domain.model.ActivityLevel
import com.practice.core.domain.model.Gender
import com.practice.core.domain.preferences.Preferences
import com.practice.core.navigation.Route
import com.practice.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {
    var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onActivityLevelSelect(activityLevel: ActivityLevel){
        selectedActivityLevel = activityLevel
    }


    fun onNextClick(){
        viewModelScope.launch{
            preferences.saveActivityLevel(selectedActivityLevel)
            _uiEvent.send(UIEvent.Navigate(Route.GOAL))
        }

    }
}