package com.metamamun.equipment.presentation.screen.onboarding

import GoogleLoginContent
import android.os.Build.VERSION_CODES
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

import com.metamamun.equipment.R
import com.metamamun.equipment.data.model.UserProfile
import com.metamamun.equipment.navigation.Graph
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.preference.SessionPreference
import com.metamamun.equipment.presentation.composables.CustomImage
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.ui.theme.Pink40
import com.metamamun.equipment.utils.getVersionNameAsString

@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun OnboardingScreen(
    navController: NavHostController?,
    preference: SessionPreference? = null
) {
    val context = LocalContext.current
    val pageCount = 5
    val pagerState = rememberPagerState(pageCount = { 5 })
    val coroutineScope = rememberCoroutineScope()

    // Get Firestore instance
    val firestore = FirebaseFirestore.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        CustomText(
            text = "Welcome to",
            color = colorResource(id = R.color.grey),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp)
        )
        CustomImage(
            imageId = R.drawable.ic_logo,
            contentDescription = "App Logo",
            modifier = Modifier
                .height(50.dp)
                .padding(top = 10.dp)
        )
        OnboardingPager(pagerState, coroutineScope, navController)

        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Pink40 else colorResource(id = R.color.app_color)
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color, CircleShape)
                        .size(6.dp)
                )
            }
        }

        GoogleLoginContent(
            context = context,
            navController = navController,
            onAuthComplete = { tokenId ->
                Toast.makeText(context, "Google login successful", Toast.LENGTH_SHORT).show()

                firestore.collection("users").document(tokenId.id)
                    .get().addOnSuccessListener { document ->
                        if (document.exists()) {
                            val userData = document.toObject(UserProfile::class.java)
                            Log.d("OnboardingScreen", "User data exist :  $userData")
                            preference?.saveUserInformation(userData!!)
                            navController?.navigate(Graph.HomeGraph) {
                                popUpTo(Screen.OnboardingScreen) { inclusive = true }
                            }
                        } else {
                            preference?.userName = tokenId.displayName ?: ""
                            preference?.userEmail = tokenId.id
                            preference?.subscriberPhoto = (tokenId.profilePictureUri ?: "").toString()
                            navController.let {
                                navController?.navigate(Screen.SignUpScreen)
                            }
                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(
                            context,
                            "Error getting user: ${exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomText(
            text = "We will never post anything without your permission",
            color = colorResource(id = R.color.app_color),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp)
        )
        CustomText(
            text = context.getVersionNameAsString(),
            color = colorResource(id = R.color.app_color),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController?.navigate(Graph.HomeGraph) {
                        popUpTo(Screen.OnboardingScreen) {
                            inclusive = true
                        }
                    }
                }
                .padding(top = 20.dp, end = 20.dp)
        )

    }
}


@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navController = null, preference = null)
}

