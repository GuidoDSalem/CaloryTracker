package com.practice.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.core.domain.preferences.Preferences
import com.practice.core.domain.use_case.FilterOutDigits
import com.practice.core.navigation.Route
import com.practice.core.util.UIEvent
import com.practice.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutdigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
): ViewModel(){
    var state by mutableStateOf(NutrientGoalState())
    private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event:NutrientGoalEvent){
        when(event){
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                state = state.copy(carbsRatio = filterOutdigits(event.ratio))
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(proteinRatio = filterOutdigits(event.ratio))
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                state = state.copy(fatRatio = filterOutdigits(event.ratio))

            }
            is NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrients(
                    state.carbsRatio,
                    state.proteinRatio,
                    state.fatRatio
                )
                when(result){
                    is ValidateNutrients.Result.Success ->{
                        preferences.saveCarbRatio(result.carbsRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveFatRatio(result.fatRatio)
                        viewModelScope.launch{
                            _uiEvent.send(UIEvent.Navigate(Route.TRACKER_OVERVIEW))
                        }
                    }
                    is ValidateNutrients.Result.Error ->{
                        viewModelScope.launch{
                            _uiEvent.send(UIEvent.ShowSnackbar(result.message))
                        }
                    }
                }
            }

        }
    }

}