package com.practice.tracker_presentation.search

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.practice.core.R
import com.practice.core.util.UIEvent
import com.practice.core_ui.LocalSpacing
import com.practice.tracker_presentation.search.components.SearchTextField

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
){
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = keyboardController){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                    keyboardController?.hide()
                }
                is UIEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceBetween
        ){
             Text(
                 text = stringResource(id = R.string.add_meal,mealName),
                 style = MaterialTheme.typography.h2,
                 //color = MaterialTheme.colors.primary,
             )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            SearchTextField(
                text = state.query,
                onValueChange = {
                    viewModel.onEvent(SearchEvent.OnQueryChange(it))
                },
                onSearch = {
                    viewModel.onEvent(SearchEvent.OnSearch)
                    },
                onFocusChanged = {
                    viewModel.onEvent(SearchEvent.OnSearchFoucusChange(it.isFocused))
                    }
            )
        }
        
    }
