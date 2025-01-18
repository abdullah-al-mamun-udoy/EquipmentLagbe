package com.metamamun.equipment.presentation.screen.terms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.preference.SessionPreference

@Composable
fun TermsAndConditionsScreen(
    navController: NavController,
    preference: SessionPreference
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        TermsAndConditionsScreenBody(
            preference = preference,
            onClick = {
                navController.navigate(Screen.HomeScreen){
                    popUpTo(Screen.PrivacyPolicyScreen){
                        inclusive = true
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SubscriptionScreenPreview() {
    TermsAndConditionsScreenBody(preference = null)
}