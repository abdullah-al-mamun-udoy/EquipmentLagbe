package com.metamamun.equipment.presentation.screen.signup

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.metamamun.equipment.R
import com.metamamun.equipment.data.model.UserProfile
import com.metamamun.equipment.navigation.Graph
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.preference.SessionPreference
import com.metamamun.equipment.presentation.composables.CircularCheckboxWithGenderCard
import com.metamamun.equipment.presentation.composables.CircularCheckboxWithTextCard
import com.metamamun.equipment.presentation.composables.CommonAgeInputCard
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.presentation.composables.CustomTextField
import com.metamamun.equipment.ui.theme.CustomFonts
import com.metamamun.equipment.ui.theme.LocalPadding
import java.util.UUID

@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@Preview
@Composable
fun SignUpScreen(
    navController: NavHostController? = null,
    preference: SessionPreference? = null
) {
    val padding = LocalPadding.current
    val firestore = FirebaseFirestore.getInstance()

    val firstName = remember { mutableStateOf(preference?.userName ?: "") }
    val age = remember { mutableStateOf("") }
    val height = remember { mutableStateOf("") }
    val weight = remember { mutableStateOf("") }
    val targetWeight = remember { mutableStateOf("") }
    var activityLevel = remember { mutableStateOf("") }
    var gender = remember { mutableStateOf("") }
    var isTermAndConditonEnabled = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .background(colorResource(R.color.white))
            .imePadding(),
    ) {
        Image(
            modifier = Modifier
                .padding(bottom = 0.dp)
                .size(120.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "splash_image",
        )

        androidx.compose.material3.Text(
            modifier = Modifier.padding(bottom = 24.dp),
            text = stringResource(R.string.app_name),
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.app_color),
            fontWeight = FontWeight.Medium,
            fontFamily = CustomFonts.fonts
        )

        CustomText(
            text = "Prefered Name",
            color = colorResource(R.color.app_color),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.extraSmall)
        )
        CustomTextField(
            modifier = Modifier.padding(
                top = padding.small,
                bottom = 16.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                colorResource(id = R.color.white)
            ),
            height = 54.dp,
            trailingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User ID"
                    )
                }
            },
            textChangeState = firstName,
            hintTextColor = colorResource(id = R.color.text_field_border_color),
            textColor = MaterialTheme.colorScheme.onPrimary,
            backgroundColor = colorResource(R.color.app_color_light),
            hintText = "Enter Full Name",
            cornerRadius = padding.small,
            singleLine = true,
        )
        CustomText(
            text = "What is your baseline activity level?",
            color = colorResource(R.color.app_color),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.extraSmall)
        )
        Spacer(modifier = Modifier.height(8.dp))
        CircularCheckboxWithTextCard(
            headerText = "Not Very Active",
            bodyText = "Spend most of the day sitting (e.g., bankteller, desk job).",
            isSelected = activityLevel.value == "Not Very Active",
            onSelect = { activityLevel.value = "Not Very Active" },

            )
        CircularCheckboxWithTextCard(
            headerText = "Lightly Active",
            bodyText = "Spend a good part of the day on your feet (e.g., walking, jogging).",
            isSelected = activityLevel.value == "Lightly Active",
            onSelect = { activityLevel.value = "Lightly Active" },

            )
        CircularCheckboxWithTextCard(
            headerText = "Active",
            bodyText = "Spend a good part of the day doing same physical activity (e.g., food server, cleaning job).",
            isSelected = activityLevel.value == "Active",
            onSelect = { activityLevel.value = "Active" },

            )
        CircularCheckboxWithTextCard(
            headerText = "Very Active",
            bodyText = "Spend a good part of the day doing same physical activity (e.g., food server, construction work).",
            isSelected = activityLevel.value == "Very Active",
            onSelect = { activityLevel.value = "Very Active" },

            )
        Spacer(modifier = Modifier.height(16.dp))

        CustomText(
            text = "Choose your gender?",
            color = colorResource(R.color.app_color),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.extraSmall)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CircularCheckboxWithGenderCard(
                headerText = "Male",
                isSelected = gender.value == "Male",
                onSelect = { gender.value = "Male" },
                modifier = Modifier.weight(0.5f) // Equal weight
            )
            Spacer(modifier = Modifier.width(10.dp))
            CircularCheckboxWithGenderCard(
                headerText = "Female",
                isSelected = gender.value == "Female",
                onSelect = { gender.value = "Female" },
                modifier = Modifier.weight(0.5f) // Equal weight
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        CustomText(
            text = "How old are you?",
            color = colorResource(R.color.app_color),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.extraSmall)
        )
        CustomTextField(
            modifier = Modifier.padding(
                top = padding.small,
                bottom = 16.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                colorResource(id = R.color.white)
            ),
            height = 54.dp,
            textChangeState = age,
            hintTextColor = colorResource(id = R.color.text_field_border_color),
            textColor = MaterialTheme.colorScheme.onPrimary,
            backgroundColor = colorResource(R.color.app_color_light),
            hintText = "Enter your age",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            cornerRadius = padding.small,
            singleLine = true,
        )
        CustomText(
            text = "How tall are you?",
            color = colorResource(R.color.app_color),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.extraSmall)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CommonAgeInputCard(
                headerText = height,
                isSelected = gender.value == "",
                onSelect = { gender.value = "" },
                hintText = "Enter your height",// Equal weight
                isEdit = true,
                modifier = Modifier.weight(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                hintTextAlignment = TextAlign.Start,
            )
            Spacer(modifier = Modifier.width(8.dp))
            CommonAgeInputCard(
                headerText = mutableStateOf(""),
                isSelected = gender.value == "",
                onSelect = { gender.value = "" },
                hintText = "CM", // Equal weight,
                isEdit = false,
                modifier = Modifier.weight(0.2f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                hintTextAlignment = TextAlign.Center,
            )
        }
        CustomText(
            text = "How much do you weigh?",
            color = colorResource(R.color.app_color),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.extraSmall)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CommonAgeInputCard(
                headerText = weight,
                isSelected = gender.value == "",
                onSelect = { gender.value = "" },
                hintText = "Enter your weight",// Equal weight
                isEdit = true,
                modifier = Modifier.weight(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                hintTextAlignment = TextAlign.Start,
            )
            Spacer(modifier = Modifier.width(8.dp))
            CommonAgeInputCard(
                headerText = mutableStateOf(""),
                isSelected = gender.value == "KG",
                onSelect = { gender.value = "KG" },
                hintText = "KG", // Equal weight,
                isEdit = false,
                modifier = Modifier.weight(0.2f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                hintTextAlignment = TextAlign.Center,
            )
        }
        CustomText(
            text = "What's your goal weight?",
            color = colorResource(R.color.app_color),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding.extraSmall)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CommonAgeInputCard(
                headerText = targetWeight,
                isSelected = gender.value == "",
                onSelect = { gender.value = "" },
                hintText = "Enter your weight",// Equal weight
                isEdit = true,
                modifier = Modifier.weight(0.8f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                hintTextAlignment = TextAlign.Start,
            )
            Spacer(modifier = Modifier.width(8.dp))
            CommonAgeInputCard(
                headerText = mutableStateOf(""),
                isSelected = gender.value == "KG",
                onSelect = { gender.value = "KG" },
                hintText = "KG", // Equal weight,
                hintTextAlignment = TextAlign.Center,
                isEdit = false,
                modifier = Modifier.weight(0.2f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        }

        //terms and condition
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.white))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(if (isTermAndConditonEnabled.value) colorResource(R.color.app_color) else Color.Gray)
                    .clickable { isTermAndConditonEnabled.value = true },
                contentAlignment = Alignment.Center
            ) {
                if (isTermAndConditonEnabled.value) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Checked",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "I agree to Fit4u2 ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.wrapContentWidth()
            )
            Text(
                text = "Terms & Conditions ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.red),
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Terms & Conditions ", Toast.LENGTH_SHORT)
                            .show()

                    }
            )
            Text(
                text = "and ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.wrapContentWidth()
            )
            Text(
                text = "Privacy Policy.",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.red),
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Privacy Policy", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val errorMessage = when {
                    firstName.value.isEmpty() -> "Please enter your name"
                    activityLevel.value.isEmpty() -> "Please select your activity level"
                    gender.value.isEmpty() -> "Please select your gender"
                    age.value.isEmpty() -> "Please enter your age"
                    height.value.isEmpty() -> "Please enter your height in centimeter"
                    weight.value.isEmpty() -> "Please enter your weight Kg"
                    targetWeight.value.isEmpty() -> "Please enter your target weight Kg"
                    !isTermAndConditonEnabled.value -> "Please accept the terms and conditions"
                    else -> null
                }
                if (errorMessage != null) {
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    return@Button
                } else {
                    Log.d(
                        "input123",
                        "SignUpScreen: ${firstName.value} ${age.value} ${height.value} ${activityLevel.value} ${gender.value} ${weight.value} ${targetWeight.value} ${isTermAndConditonEnabled.value}"
                    )

                    val userData = UserProfile(
                        id = UUID.randomUUID().toString(),
                        username = firstName.value,
                        profilePicture = preference?.subscriberPhoto ?: "",
                        email = preference?.userEmail ?: "",
                        age = age.value,
                        sex = gender.value,
                        height = height.value,
                        weight = weight.value,
                        weightGoal = targetWeight.value,
                        registrationCompleted = isTermAndConditonEnabled.value,
                    )
                    // Save user data in the "users" collection with the ID as the document ID
                    firestore.collection("users").document(preference?.userEmail ?: "")
                        .set(userData)
                        .addOnSuccessListener {
                            preference?.saveUserInformation(userData)
                            Toast.makeText(
                                context,
                                "Registration completed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController?.navigate(Graph.HomeGraph) {
                                popUpTo(Screen.SignUpScreen) { inclusive = true }
                            }
                            Log.d("Firestore", "User saved successfully.")
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(
                                context,
                                "Registration failed. Try again later.",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.e("Firestore", "Error saving user: ${exception.message}")
                        }
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.app_color)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            androidx.compose.material3.Text(
                text = "Sign Up",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }

}