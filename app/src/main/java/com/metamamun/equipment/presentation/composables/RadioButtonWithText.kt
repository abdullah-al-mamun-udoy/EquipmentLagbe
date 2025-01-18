package com.metamamun.equipment.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metamamun.equipment.R
import com.metamamun.equipment.ui.theme.padding

@Preview
@Composable
fun CircularCheckboxWithTextCard(
    headerText: String = "",
    bodyText: String = "",
    isSelected: Boolean = false,
    onSelect: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onSelect() },
        border = BorderStroke(
            1.dp,
            color = if (isSelected) colorResource(R.color.app_color) else colorResource(R.color.white)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.app_color_light))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f), // Take most of the space
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = headerText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = bodyText,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }

            // Circular Checkbox
            Spacer(modifier = Modifier.width(8.dp))
            // Circular Checkbox
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) colorResource(R.color.app_color) else Color.Gray)
                    .clickable { onSelect() }, // Handle click
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Checked",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun CircularCheckboxWithGenderCard(
    headerText: String = "",
    isSelected: Boolean = false,
    onSelect: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { onSelect() },
        border = BorderStroke(
            1.dp,
            color = if (isSelected) colorResource(R.color.app_color) else colorResource(R.color.white)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.app_color_light))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = headerText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) colorResource(R.color.app_color) else Color.Gray)
                    .clickable { onSelect() },
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Checked",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CommonAgeInputCard(
    headerText: MutableState<String> = mutableStateOf(""),
    isSelected: Boolean = false,
    onSelect: () -> Unit = {},
    hintText: String = "",
    isEdit: Boolean = true,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    hintTextAlignment: TextAlign
) {
        CustomTextField(
            modifier = modifier.padding(
                top = 8.dp,
                bottom = 16.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                colorResource(id = R.color.white)
            ),
            height = 54.dp,
            textChangeState = headerText,
            hintTextColor = colorResource(id = R.color.text_field_border_color),
            textColor = colorResource(R.color.black),
            backgroundColor = colorResource(R.color.app_color_light),
            hintText = hintText,
            hintTextAlignment = hintTextAlignment,
            cornerRadius = padding.small,
            singleLine = true,
            editable = isEdit,
            keyboardOptions = keyboardOptions
        )
}
