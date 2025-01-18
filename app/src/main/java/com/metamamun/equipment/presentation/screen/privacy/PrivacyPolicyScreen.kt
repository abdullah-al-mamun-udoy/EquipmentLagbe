package com.metamamun.equipment.presentation.screen.privacy

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.preference.SessionPreference

@Composable
fun PrivacyPolicyScreen(
    navController: NavController?,
    preference: SessionPreference? = null
) {
    PrivacyPolicyScreenBody(
        preference = preference,
        onClick = {
            navController?.navigate(Screen.HomeScreen) {
                popUpTo(Screen.PrivacyPolicyScreen) {
                    inclusive = true
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    PrivacyPolicyScreen(navController = null, preference = null)
}

