package com.practice.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.practice.core.R
import com.practice.core.navigation.Route
import com.practice.core.util.UIEvent
import com.practice.core_ui.LocalSpacing
import com.practice.onboarding_presentation.components.ActionButton


@Composable
fun WelcomeScreen(
    onNavigate:(UIEvent.Navigate)->Unit
) {
    val spacing = LocalSpacing.current
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(id = R.string.welcome_text),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = R.string.next),
            modifier = Modifier.align(CenterHorizontally),
            onClick = { onNavigate(UIEvent.Navigate(Route.AGE)) }

        )
        

    }
}