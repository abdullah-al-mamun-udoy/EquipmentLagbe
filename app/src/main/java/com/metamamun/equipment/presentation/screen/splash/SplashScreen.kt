package com.metamamun.equipment.presentation.screen.splash

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.metamamun.equipment.R
import com.metamamun.equipment.data.model.UserProfile
import com.metamamun.equipment.navigation.Graph
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.preference.SessionPreference
import com.metamamun.equipment.presentation.composables.CustomImage
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.presentation.screen.myplan.PlanDataDTO
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController? = null,
    preference: SessionPreference
) {
    val context = LocalContext.current
    val firestore = FirebaseFirestore.getInstance()

    LaunchedEffect(key1 = true) {
        delay(2000).apply {
            if (preference.isVerifiedUser) {
                firestore.collection("users").document(preference.userEmail)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            val userData = document.toObject(UserProfile::class.java)

                            preference.saveUserInformation(userData!!)
                            navController?.navigate(Graph.HomeGraph) {
                                popUpTo(Screen.SplashScreen) { inclusive = true }
                            }
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            "Failed to login. Try again later.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

//                storeUserProfiles(plans)

            } else {
                if(preference.termsAndConditions.isEmpty() || preference.privacyPolicy.isEmpty())
                {
                    firestore.collection("appConfig").document("additional")
                        .get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                preference.termsAndConditions = document.getString("termsAndConditions") ?: ""
                                preference.privacyPolicy = document.getString("privacyPolicy") ?: ""
                            }
                        }
                        .addOnFailureListener {
                            Log.d("SplashScreen", "Failed to fetch terms and conditions and privacy policy")
                        }
                }
                navController?.navigate(Screen.OnboardingScreen) {
                    popUpTo(Screen.SplashScreen) {
                        inclusive = true
                    }
                }
            }
        }
    }
    SplashScreenBody()
}

fun storeUserProfiles(userProfiles: List<PlanDataDTO>) {
    val firestore = FirebaseFirestore.getInstance()

    // Convert the list of UserProfile objects to a map or JSON-like structure
    val userProfilesMap = hashMapOf("plans" to userProfiles)

    // Store the data in Firestore under appConfig -> plans
    firestore.collection("appConfig")
        .document("plans")
        .set(userProfilesMap)
        .addOnSuccessListener {
            // Successfully stored
            Log.d("SplashScreen", "User profiles stored successfully")
        }
        .addOnFailureListener { e ->
            // Handle failure
            Log.d("SplashScreen", "Error storing user profiles: ${e.message}")
            println("Error storing user profiles: ${e.message}")
        }
}

val plans = listOf(
    PlanDataDTO(
        id = "1",
        header = "Weight Loss Plan",
        duration = "30 Days",
        timesPerWeek = "Twice Daily",
        difficulty = "Beginner",
        overView = "A comprehensive plan to help you lose weight effectively.",
        whyWillYouChoose = "Effective and easy to follow",
        whyWillYouChooseDetails = "• Tailored for beginners\n• Easy to follow\n• Proven results",
        whatYouWillDo = "Daily exercises and diet plan",
        whatYouWillDoDetails = "• Morning jog\n• Evening yoga\n• Balanced diet",
        guideline = "Follow the plan strictly",
        guidelineDetails = "• Stick to the schedule\n• Maintain a balanced diet\n• Stay hydrated",
        imageResId = R.drawable.ic_plan_1.toString(),
        firstWeek = "Week 1 plan details...",
        secondWeek = "Week 2 plan details...",
        thirdWeek = "Week 3 plan details...",
        fourthWeek = "Week 4 plan details..."
    ),
    PlanDataDTO(
        id = "2",
        header = "Muscle Gain Plan",
        duration = "60 Days",
        timesPerWeek = "Daily",
        difficulty = "Intermediate",
        overView = "A plan designed to help you gain muscle mass.",
        whyWillYouChoose = "Build muscle effectively",
        whyWillYouChooseDetails = "• Focused on muscle gain\n• Includes strength training\n• Nutritional guidance",
        whatYouWillDo = "Strength training and protein-rich diet",
        whatYouWillDoDetails = "• Weight lifting\n• Protein shakes\n• High-protein meals",
        guideline = "Consistency is key",
        guidelineDetails = "• Follow the workout routine\n• Eat protein-rich foods\n• Rest adequately",
        imageResId = R.drawable.ic_plan_2.toString(),
        firstWeek = "Week 1 plan details...",
        secondWeek = "Week 2 plan details...",
        thirdWeek = "Week 3 plan details...",
        fourthWeek = "Week 4 plan details..."
    ),
     PlanDataDTO(
        id = "3",
        header = "Muscle Gain Plan",
        duration = "60 Days",
        timesPerWeek = "Daily",
        difficulty = "Intermediate",
        overView = "A plan designed to help you gain muscle mass.",
        whyWillYouChoose = "Build muscle effectively",
        whyWillYouChooseDetails = "• Focused on muscle gain\n• Includes strength training\n• Nutritional guidance",
        whatYouWillDo = "Strength training and protein-rich diet",
        whatYouWillDoDetails = "• Weight lifting\n• Protein shakes\n• High-protein meals",
        guideline = "Consistency is key",
        guidelineDetails = "• Follow the workout routine\n• Eat protein-rich foods\n• Rest adequately",
        imageResId = R.drawable.ic_plan_3.toString(),
        firstWeek = "Week 1 plan details...",
        secondWeek = "Week 2 plan details...",
        thirdWeek = "Week 3 plan details...",
        fourthWeek = "Week 4 plan details..."
    )
)



@Preview(showBackground = true)
@Composable
fun SplashScreenBody() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        contentAlignment = Alignment.Center
    ) {
        CustomImage(
            imageId = R.drawable.ic_logo,
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
        CustomText(
            text = "Fit4u2",
            color = colorResource(id = R.color.app_color),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 150.dp)
        )
    }
}