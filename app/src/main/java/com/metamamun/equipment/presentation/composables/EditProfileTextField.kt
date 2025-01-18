package com.metamamun.equipment.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.metamamun.equipment.ui.theme.Pink40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextField(
    textChangeState: MutableState<String>,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = TextFieldDefaults.MinHeight, // Use default height for better alignment
    border: BorderStroke? = null,
    singleLine: Boolean = false,
    textStyle: TextStyle = TextStyle(),
    cornerRadius: Dp = 100.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    hintText: String = "Enter text here",
    hintTextColor: Color = colorResource(id = R.color.black),
    hintTextAlignment: TextAlign = TextAlign.Start,
    textColor: Color = colorResource(id = R.color.teal_700),
    leadingIconColor: Color = Color(0XFFC4C4C4),
    trailingIconColor: Color = Color(0XFFC4C4C4),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    editable: Boolean = true,
    onClick: () -> Unit = {},
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        value = textChangeState.value,
        onValueChange = {
            textChangeState.value = it
            onValueChange.invoke(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Pink40,
            unfocusedIndicatorColor = Color.Gray,
            focusedPlaceholderColor = hintTextColor,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            cursorColor = Color(0XFFC4C4C4),
            focusedTrailingIconColor = trailingIconColor,
            unfocusedTrailingIconColor = trailingIconColor,
            focusedLeadingIconColor = leadingIconColor,
            unfocusedLeadingIconColor = leadingIconColor,
            containerColor = Color.Transparent // Remove background color
        ),
        singleLine = singleLine,
        enabled = editable,
        textStyle = textStyle.copy(color = textColor), // Ensure consistent textStyle
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = hintTextAlignment,
                style = textStyle.copy(color = hintTextColor), // Use the same textStyle for placeholder
                text = hintText
            )
        }
    )
}

