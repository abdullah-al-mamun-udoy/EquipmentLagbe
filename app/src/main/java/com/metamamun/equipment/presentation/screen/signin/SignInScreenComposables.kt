import android.content.Context
import android.credentials.GetCredentialException
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.metamamun.equipment.R
import com.metamamun.equipment.presentation.composables.CustomButton
import com.metamamun.equipment.ui.theme.padding
import com.stevdzasan.onetap.rememberOneTapSignInState
import kotlinx.coroutines.launch
import timber.log.Timber
import java.security.MessageDigest
import java.util.UUID

//package com.lazydeveloper.fit4u2.presentation.screen.signin
//
//import android.widget.Toast
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.PagerState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.lazydeveloper.fit4u2.R
//import com.lazydeveloper.fit4u2.enums.InputType
//import com.lazydeveloper.fit4u2.presentation.composables.CustomButton
//import com.lazydeveloper.fit4u2.presentation.composables.CustomImage
//import com.lazydeveloper.fit4u2.presentation.composables.CustomText
//import com.lazydeveloper.fit4u2.presentation.composables.CustomTextField
//import com.lazydeveloper.fit4u2.ui.theme.LightGrey
//import com.lazydeveloper.fit4u2.ui.theme.Pink40
//import com.lazydeveloper.fit4u2.ui.theme.Transparent
//import com.lazydeveloper.fit4u2.ui.theme.White
//import com.lazydeveloper.fit4u2.ui.theme.padding
//import com.lazydeveloper.fit4u2.utils.isValid
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//@Composable
//fun SignInPager(
//    pagerState: PagerState,
//    coroutineScope: CoroutineScope,
//    navController: NavHostController
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        CustomImage(
//            imageId = R.drawable.field,
//            contentDescription = "Logo",
//            modifier = Modifier
//                .fillMaxSize(),
//            contentScale = ContentScale.FillBounds
//        )
//        HorizontalPager(
//            state = pagerState,
//            userScrollEnabled = false,
//            modifier = Modifier
//                .background(Transparent)
//        ) { page ->
//            when (page) {
//                0 -> SignInContent(
//                    onClick = {
//                        coroutineScope.launch {
//                            pagerState.animateScrollToPage(1)
//                        }
//                    }
//                )
//
//                1 -> OTPVerificationContent(
//                    onClick = {
//                        navController.popBackStack()
//                    }
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SignInContent(
//    onClick: () -> Unit = {}
//) {
//    val context = LocalContext.current
//    val color = MaterialTheme.colorScheme
//    val typo = MaterialTheme.typography
//    val numberState = remember { mutableStateOf("") }
//
//    fun handleLogin() {
//        var phoneNo = numberState.value.trim()
//        phoneNo = when {
//            phoneNo.startsWith("88") -> "+$phoneNo"
//            phoneNo.startsWith("80") -> "+8$phoneNo"
//            phoneNo.startsWith("01") -> "+88$phoneNo"
//            phoneNo.startsWith("1") -> "+880$phoneNo"
//            else -> phoneNo
//        }
//        numberState.value = phoneNo
//    }
//    Column(
//        modifier = Modifier
//            .padding(horizontal = padding.largeMedium)
//            .fillMaxSize()
//            .background(Transparent),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        CustomText(
//            text = "Welcome",
//            style = MaterialTheme.typography.headlineSmall,
//            color = MaterialTheme.colorScheme.tertiary,
//            modifier = Modifier.align(Alignment.Start),
//        )
//        CustomText(
//            text = "T sports",
//            style = MaterialTheme.typography.displayMedium,
//            color = MaterialTheme.colorScheme.tertiary,
//            modifier = Modifier.align(Alignment.Start),
//        )
//        CustomText(
//            text = "First ever sports channel in Bangladesh",
//            style = MaterialTheme.typography.labelMedium,
//            color = MaterialTheme.colorScheme.tertiary,
//            modifier = Modifier
//                .align(Alignment.Start)
//                .padding(bottom = padding.extraLargeMedium),
//        )
//        CustomText(
//            text = "Please enter your Mobile Number\n" +
//                    "to get OTP(One Time Passwaord)",
//            style = MaterialTheme.typography.bodyMedium,
//            color = color.tertiary,
//            modifier = Modifier
//
//                .align(Alignment.Start),
//        )
//        CustomTextField(
//            modifier = Modifier.padding(top = padding.medium),
//            textChangeState = numberState,
//            cornerRadius = padding.small,
//            border = BorderStroke(1.dp, LightGrey),
//            hintText = "+880 00 000 0000",
//            hintTextColor = LightGrey,
//            backgroundColor = Transparent,
//            //backgroundColor = MaterialTheme.colorScheme.tertiary,
//            //hintTextColor = MaterialTheme.colorScheme.onPrimary,
//        )
//        CustomButton(
//            buttonText = "Generate OTP",
//            showIcon = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = padding.medium)
//                .height(padding.extraLargeMedium)
//                .clip(RoundedCornerShape(padding.small))
//                .background(Pink40)
//                .clickable {
//                    handleLogin()
//                    if ((numberState.value.isValid(InputType.PHONE))) {
//                        onClick.invoke()
////                        mPref.subscriberMSISDN = phoneNo
////                        mPref.loginType = "1"
////                        showTermsAndConditionsDialog()
//                    } else {
//                        Toast
//                            .makeText(context, "Invalid Phone Number", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                },
//        )
//
//        Divider()
//        GoogleLoginContent()
//        Spacer(modifier = Modifier.height(padding.medium))
//        FacebookLoginContent()
//    }
//}
//
//@Preview(showBackground = false)
//@Composable
//fun Divider(){
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = padding.medium),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box(
//            modifier = Modifier
//                .height(1.dp)
//                .fillMaxWidth()
//                .weight(1f)
//                .padding(horizontal = padding.smallMedium)
//                .background(LightGrey)
//        )
//        CustomText(
//            text = "OR",
//            style = MaterialTheme.typography.labelSmall,
//            color = MaterialTheme.colorScheme.tertiary,
//        )
//        Box(
//            modifier = Modifier
//                .height(1.dp)
//                .fillMaxWidth()
//                .weight(1f)
//                .padding(horizontal = padding.smallMedium)
//                .background(LightGrey)
//        )
//    }
//}
//
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Preview(showBackground = true)
@Composable
fun GoogleLoginContent(
    navController: NavHostController? = null,
    context: Context? = LocalContext.current,
    onAuthComplete: (GoogleIdTokenCredential) -> Unit = {},
    ) {
    val lifecycleScope = androidx.lifecycle.compose.LocalLifecycleOwner.current.lifecycleScope
    val state = rememberOneTapSignInState()
    val rawNoence = UUID.randomUUID()
        .toString()
    val bytes = rawNoence.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    val noence = digest.fold("") { str, it -> str + "%02x".format(it) }
    val credentialManager = CredentialManager.create(context!!)
    val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(context.getString(R.string.web_client_id))
        .setNonce(noence)
        .build()
    val request: GetCredentialRequest = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()
    CustomButton(
        buttonText = "Sign in with Google",
        buttonTextColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding.medium)
            .height(padding.extraLargeMedium)
            .clip(RoundedCornerShape(padding.small))
            .background(colorResource(id = R.color.app_color))
            .clickable {
                lifecycleScope.launch {
                    try {
                        val result = credentialManager.getCredential(
                            request = request,
                            context = context!!,
                        )
                        val credential = result.credential
                        val googleTokenId = GoogleIdTokenCredential.createFrom(credential.data)
                        val googleToken = googleTokenId.idToken
                        onAuthComplete.invoke(googleTokenId)
                        Timber.tag("SignInScreen").e("Google token: $googleToken")
                    } catch (e: GetCredentialException) {
                        Timber.tag("SignInScreen").e("Error: ${e.message}")
                        if (e.message == "Cannot find a matching credential.") {
                            Timber.tag("SignInScreen").e("Error: ${e.message}")
                        }
                    } catch (e: GetCredentialCancellationException) {
                        Timber.tag("SignInScreen").e("Credential request cancelled by ${e.message}")
                        Timber.tag("SignInScreen")
                            .e("Credential request cancelled by ${e.printStackTrace()}")
                    } catch (e: Exception) {
                        Timber.tag("SignInScreen").e("Error getting credential: ${e.message}")
                    }
                }




            },
        iconId = R.drawable.ic_google,
    )
}
//@Preview(showBackground = true)
//@Composable
//fun FacebookLoginContent() {
//    CustomButton(
//        buttonText = "sign In with Facebook",
//        buttonTextColor = Color.Gray,
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(padding.extraLargeMedium)
//            .clip(RoundedCornerShape(padding.small))
//            .background(White),
//        iconId = R.drawable.ic_facebook,
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun OTPVerificationContent(
//    onClick: () -> Unit = {}
//) {
//    var resendBtnPressCount: Int = 0
//    val color = MaterialTheme.colorScheme
//    val typo = MaterialTheme.typography
//    val pinState = remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .padding(horizontal = padding.medium)
//            .fillMaxSize()
//            .background(Transparent),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        CustomText(
//            text = "OTP Verification",
//            style = MaterialTheme.typography.headlineMedium,
//            color = MaterialTheme.colorScheme.tertiary,
//            modifier = Modifier.align(Alignment.Start),
//        )
//        CustomText(
//            text = "Enter OTP send to +882165400",
//            style = MaterialTheme.typography.bodyMedium,
//            color = color.tertiary,
//            modifier = Modifier
//                .padding(bottom = padding.large)
//                .align(Alignment.Start),
//        )
//        PinCodeTextField(pin = pinState)
//
//        val context = LocalContext.current
//        CustomButton(
//            buttonText = "Verify",
//            showIcon = false,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = padding.medium)
//                .height(padding.largeMedium)
//                .clip(RoundedCornerShape(padding.small))
//                .background(Pink40)
//                .clickable {
//                    onClick.invoke()
//                    Toast
//                        .makeText(context, "OTP : $pinState", Toast.LENGTH_SHORT)
//                        .show()
//                },
//        )
//        Row(
//            modifier = Modifier
//                .padding(
//                    top = padding.large,
//                    bottom = padding.mediumLarge
//                )
//        ) {
//            CustomText(
//                text = "Didn't receive the OTP?",
//                style = typo.labelLarge,
//                color = color.tertiary,
//            )
//            CustomText(
//                text = "Resend OTP",
//                style = typo.labelLarge,
//                color = color.primary,
//                modifier = Modifier
//                    .padding(start = padding.extraSmall),
//            )
//        }
//        Box(
//            modifier = Modifier
//                .height(1.dp)
//                .fillMaxWidth()
//                .padding(horizontal = padding.smallMedium)
//                .background(LightGrey)
//        )
//        CustomText(
//            text = "By signing, you affirm you have read and agree to the T-Sports Terms, and you agree and authorize T-sports and its affiliates, and their networks of service professionals, to deliver marketing calls or texts using automated technology to the number you provided above regarding your project and other home services offers. Consent is not a condition of purchase.",
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.labelSmall,
//            color = color.onSecondary,
//            modifier = Modifier
//                .padding(top = padding.medium)
//                .align(Alignment.CenterHorizontally),
//        )
//    }
//}
//
//@Composable
//fun PinCodeTextField(pinLength: Int = 6, pin: MutableState<String>) {
//    val focusRequesters = remember { List(pinLength) { FocusRequester() } }
//    var isDoneEntering by remember { mutableStateOf(false) }
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceAround
//    ) {
//        (0 until pinLength).forEach { index ->
//            val isLastIndex = index == pinLength - 1
//            val keyboardType = KeyboardType.Number
//            val keyboardOptions = KeyboardOptions(
//                keyboardType = keyboardType,
//                imeAction = if (isLastIndex) ImeAction.Done else ImeAction.Next
//            )
//            Card(
//                shape = RoundedCornerShape(padding.small),
//                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.Red
//                )
//            ) {
//                TextField(
//                    value = pin.value.getOrNull(index)?.toString() ?: "",
//                    onValueChange = { newChar ->
//                        if (newChar.isNotEmpty() && !isLastIndex) {
//                            focusRequesters[index + 1].requestFocus()
//                        }
//                        pin.value = if (index < pin.value.length) {
//                            pin.value.toMutableList().also {
//                                it[index] = newChar.takeLast(1).firstOrNull() ?: ' '
//                            }.joinToString(separator = "")
//                        } else {
//                            pin.value + newChar.takeLast(1)
//                        }
//                        isDoneEntering = pin.value.length == pinLength
//                    },
//                    keyboardOptions = keyboardOptions,
//                    keyboardActions = KeyboardActions(
//                        onNext = {
//                            if (!isLastIndex) {
//                                focusRequesters[index + 1].requestFocus()
//                            }
//                        },
//                        onDone = {
//                            isDoneEntering = true
//                        }
//                    ),
//                    maxLines = 1,
//                    singleLine = true,
//                    modifier = Modifier
//                        .width(50.dp)
//                        .focusRequester(focusRequesters[index]),
//                    textStyle = TextStyle.Default.copy(color = Color.Black),
//                    isError = isDoneEntering && pin.value.length != pinLength,
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
//                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
////                        focusedPlaceholderColor = hintTextColor,
////                        focusedTextColor = textColor,
////                        unfocusedTextColor = textColor,
////                        cursorColor = Color(0XFFC4C4C4),
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
////                        focusedTrailingIconColor = trailingIconColor,
////                        unfocusedTrailingIconColor = trailingIconColor,
////                        focusedLeadingIconColor = leadingIconColor,
////                        unfocusedLeadingIconColor = leadingIconColor,
//                    ),
//                )
//            }
//
//        }
//    }
//}