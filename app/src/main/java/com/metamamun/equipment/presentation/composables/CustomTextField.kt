package com.metamamun.equipment.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.metamamun.equipment.R

@Composable
fun CustomTextField(
    textChangeState: MutableState<String>,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = 50.dp,
    border: BorderStroke? = null,
    singleLine: Boolean = false,
    textStyle: TextStyle = TextStyle(),
    cornerRadius: Dp = 100.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    hintText: String = "Enter text here",
    hintTextColor: Color = colorResource(id = R.color.black),
    hintTextAlignment: TextAlign = TextAlign.Start,
    //backgroundColor: Color = Color(0XFF424242),
    backgroundColor: Color = colorResource(id = R.color.purple_500),
    textColor: Color = colorResource(id = R.color.teal_700),
    leadingIconColor: Color = Color(0XFFC4C4C4),
    trailingIconColor: Color = Color(0XFFC4C4C4),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    editable: Boolean = true,
    onClick: () -> Unit = { },
) {

    Card(
        shape = RoundedCornerShape(cornerRadius),
        border = border,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = modifier
            .clickable {
                onClick.invoke()
            },

        ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            value = textChangeState.value,
            onValueChange = {
                textChangeState.value = it
                onValueChange.invoke(it)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedPlaceholderColor = hintTextColor,
                focusedTextColor = colorResource(R.color.black),
                unfocusedTextColor = colorResource(R.color.black),
                cursorColor = Color(0XFFC4C4C4),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTrailingIconColor = trailingIconColor,
                unfocusedTrailingIconColor = trailingIconColor,
                focusedLeadingIconColor = leadingIconColor,
                unfocusedLeadingIconColor = leadingIconColor,
                disabledContainerColor = colorResource(R.color.app_color_light),
                disabledTextColor = colorResource(R.color.border_color),




            ),
//            colors = TextFieldDefaults.textFieldColors(
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                focusedTrailingIconColor = trailingIconColor,
//                unfocusedTrailingIconColor = trailingIconColor,
//                focusedLeadingIconColor = leadingIconColor,
//                unfocusedLeadingIconColor = leadingIconColor,
//                containerColor = backgroundColor,
//                placeholderColor = hintTextColor,
//                textColor = textColor,
//                cursorColor = Color(0XFFC4C4C4),
//            ),
            singleLine = singleLine,
            enabled = editable,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = hintTextAlignment,
                    style = MaterialTheme.typography.labelLarge,
                    text = hintText,
                    color = hintTextColor,
                )
            }
        )
    }
}