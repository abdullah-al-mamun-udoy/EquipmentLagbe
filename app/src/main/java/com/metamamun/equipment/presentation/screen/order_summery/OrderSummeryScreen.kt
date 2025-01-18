package com.metamamun.equipment.presentation.screen.order_summery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.presentation.composables.CustomButton
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.ui.theme.padding

@Composable
fun OrderSummeryScreen(
    arg: Screen.OrderSummeryScreen? = null
) {
    val isTermsAndConditionsChecked = remember { mutableStateOf(false) }
    val isDialogVisible = rememberSaveable { mutableStateOf(false) }

    DialogBox(
        isDialogVisible = isDialogVisible,
        data = arg,
        onConfirmClick = { isDialogVisible.value = false },
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = padding.medium)
    ) {
        CustomText(
            text = "Order Summery",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(vertical = padding.medium)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.W600
        )
        Card(
            elevation = CardDefaults.elevatedCardElevation(padding.lightSmall),
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column {
                OrderSummeryScreenBody(arg = arg)
                Spacer(modifier = Modifier.height(padding.mediumLarge))
                TermsAndConditions(
                    checked = isTermsAndConditionsChecked.value,
                    onCheckedChange = { isChecked ->
                        isTermsAndConditionsChecked.value = isChecked
                    }
                )
                CustomButton(
                    buttonText = "Checkout Now",
                    showIcon = false,
                    modifier = Modifier
                        .padding(horizontal = padding.medium, vertical = padding.medium)
                        .fillMaxWidth()
                        .height(padding.largeMedium)
                        .clip(RoundedCornerShape(padding.small))
                        .background(if(isTermsAndConditionsChecked.value) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface)
                        .clickable {
                            if (isTermsAndConditionsChecked.value) {
                                isDialogVisible.value = true
                            }
                        }
                )
                CustomButton(
                    buttonText = "Cancel",
                    showIcon = false,
                    modifier = Modifier
                        .padding(horizontal = padding.medium)
                        .padding(bottom = padding.mediumLarge)
                        .fillMaxWidth()
                        .height(padding.largeMedium)
                        .clip(RoundedCornerShape(padding.small))
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .clickable { isDialogVisible.value = false },
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun OrderSummeryScreenPreview() {
    OrderSummeryScreen()
}