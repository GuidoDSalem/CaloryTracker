package com.practice.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.practice.core.R
import com.practice.core.util.UIEvent
import com.practice.core_ui.LocalSpacing
import com.practice.tracker_presentation.components.AddButton
import com.practice.tracker_presentation.components.DaySelector
import com.practice.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.practice.tracker_presentation.tracker_overview.components.NutrientsHeader
import com.practice.tracker_presentation.tracker_overview.components.TrackedFoodItem

@ExperimentalCoilApi
@Composable
fun TrackerOverviewScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
    ) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UIEvent.Navigate -> onNavigate(event)
                else -> Unit
            }

        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ){
        item{
            NutrientsHeader(state = state)
            DaySelector(
                date = state.date,
                onPreviousDayClick = {
                     viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
                onNextDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

        }
        items(state.meals){meal ->
            ExpandableMeal(meal = meal,
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.spaceSmall),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ){
                        state.trackedFood.forEach{ food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeletClick = { viewModel
                                    .onEvent(TrackerOverviewEvent.OnDeleteTrackedFoodClick(food)) }
                            )
                            Spacer(modifier = Modifier.height(spacing.spaceMedium))
                            
                        }
                        AddButton(text = stringResource(
                            id = R.string.add_meal,meal.name.asString(context)),
                            onClick = { viewModel.onEvent(TrackerOverviewEvent.OnAddFoodClick(meal))},
                            modifier = Modifier.fillMaxWidth()
                        )
                         
                    }
                },
                onToggleClick = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal)) },
                modifier = Modifier.fillMaxWidth())
        }
    }
}