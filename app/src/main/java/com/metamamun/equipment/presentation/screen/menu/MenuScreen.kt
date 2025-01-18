package com.metamamun.equipment.presentation.screen.menu

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.metamamun.equipment.MainActivity
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.preference.SessionPreference
import com.metamamun.equipment.ui.theme.padding

@Composable
fun MenuScreen(
    navController: NavController?,
    preference: SessionPreference? = null
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = padding.small)
            .padding(top = padding.medium)
    ) {
        ProfileHeader(
            userProfilePic = preference?.subscriberPhoto ?: "",
            userName = preference?.userName ?: "",
            userEmail = preference?.userEmail ?: "",
            onClick = {
                navController?.navigate(Screen.EditProfileScreen) {
                    popUpTo(Screen.MenuScreen) {
                        inclusive = false
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(padding.largeMedium))
        MenuScreenSubItem(
            text = "Terms & Conditions",
            onClick = {
                navController?.navigate(Screen.TermsAndConditionsScreen)
            }
        )
        MenuScreenSubItem(
            text = "Privacy Policy",
            onClick = {
                navController?.navigate(Screen.PrivacyPolicyScreen)
            }
        )
        MenuScreenSubItem(
            text = "Log Out",
            onClick = {
                preference?.clearAll()

                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                (context as? Activity)?.finish()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MenuScreen(navController = null, preference = null)
}



