package com.metamamun.equipment.presentation.screen.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.metamamun.equipment.R
import com.metamamun.equipment.presentation.composables.CustomImage
import com.metamamun.equipment.presentation.composables.CustomImageAsync
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.ui.theme.padding

@Preview(showBackground = true)
@Composable
fun MenuScreenSubItem(
    text: String = "My Plan",
    imageId: Int = R.drawable.ic_right,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(padding.doubleExtraLarge)
            .padding(vertical = padding.small)
            .clip(RoundedCornerShape(padding.small))
            .border(
                padding.lightSmall,
                MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.2f),
                RoundedCornerShape(padding.small)
            )
            .clickable { onClick.invoke() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = padding.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.app_color),
            )

            if(text != "Sign In")
                CustomImage(
                    imageId = R.drawable.ic_right,
                    modifier = Modifier.size(padding.medium),
                    contentDescription = "arrow right"
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileHeader(
    userProfilePic: String = "",
    userName: String = "John Doe",
    userEmail: String = "johndoe20",
    onClick: () -> Unit = {}
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(padding.tripleExtraLarge)
            .clip(RoundedCornerShape(padding.small))
            .background(colorResource(id = R.color.app_color)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(padding.mediumLarge))
        Box(
            modifier = Modifier
                .size(padding.doubleExtraLarge)
                .clip(CircleShape)
                .background(Color.Gray)
                .border(
                    padding.extraSmall,
                    colorResource(id = R.color.app_color_light),
                    CircleShape
                )
        ){
            CustomImageAsync(
                imageUrl = userProfilePic,
                contentDescription = "profile",
                modifier = Modifier.fillMaxSize()
            )
//            CustomImage(
//                imageId  = R.drawable.profile,
//                contentDescription = "profile",
//                modifier = Modifier.fillMaxSize())
        }
        Column(
            modifier = Modifier
                .padding(start = padding.medium)
        ) {
            CustomText(
                text = userName,
                style = MaterialTheme.typography.headlineSmall,
                color = colorResource(id = R.color.white)
            )
            CustomText(
                text = userEmail,
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(id = R.color.white)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Edit Profile")
                    }
                },
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(top = padding.extraSmall)
                    .clickable { onClick.invoke() }
            )
        }
    }
}