package com.metamamun.equipment.presentation.screen.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.metamamun.equipment.R
import com.metamamun.equipment.presentation.composables.CustomButton
import com.metamamun.equipment.presentation.composables.CustomImage
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.presentation.composables.EditTextField
import com.metamamun.equipment.ui.theme.BlackLight
import com.metamamun.equipment.ui.theme.Grey
import com.metamamun.equipment.ui.theme.Pink40
import com.metamamun.equipment.ui.theme.padding

@Preview(showBackground = true)
@Composable
fun EditProfileScreen(
    navController: NavController? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp)
    ) {
        ProfilePhoto()
        Spacer(modifier = Modifier.height(padding.mediumLarge))
        EditProfileTextField(
            title = "Full Name",
            hintText = "Enter your name"
        )
        EditProfileTextField(
            title = "Username",
            hintText = "Enter your username"
        )
        EditProfileTextField(
            title = "Phone Number",
            hintText = "Enter your phone number"
        )
        EditProfileTextField(
            title = "Password",
            hintText = "Enter your phone password"
        )
        Spacer(modifier = Modifier.height(padding.largeMedium))
        CustomButton(
            buttonText = "Update",
            showIcon = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(padding.largeMedium)
                .clip(RoundedCornerShape(padding.small))
                .background(Pink40)
                .clickable { },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePhoto(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(148.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(148.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(
                    2.dp,
                    Grey.copy(alpha = 0.5f),
                    CircleShape
                )
        ) {
            Box(
                modifier = Modifier
                    .size(135.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)

            ){
                CustomImage(
                    imageId  = R.drawable.profile,
                    contentDescription = "profile",
                    modifier = Modifier.fillMaxSize())
            }
        }

        CustomImage(
            imageId  = R.drawable.ic_camera,
            contentDescription = "camera",
            modifier = Modifier
                .padding(top = 80.dp, start = 120.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileTextField(
    title: String = "User Name",
    hintText: String = "Enter your name"
){
    val textChangeState = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = BlackLight,
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "edit_icon",
                tint = Grey,
                modifier = Modifier.size(13.39.dp)
            )
        }

        EditTextField(
            textChangeState = textChangeState,
            hintText = hintText,
            hintTextAlignment = TextAlign.Start,
            textColor = BlackLight,
            hintTextColor = Grey,
            height = 50.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}