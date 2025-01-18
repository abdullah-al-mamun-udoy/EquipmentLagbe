package com.metamamun.equipment.presentation.screen.order_summery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.presentation.composables.CustomDialog
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.ui.theme.padding

@Preview(showBackground = true)
@Composable
fun OrderSummeryScreenBody(
    arg: Screen.OrderSummeryScreen? = null
) {
    Column(modifier = Modifier
        .padding(horizontal = padding.medium)
        .fillMaxWidth()
        .wrapContentHeight()
    ){
        Spacer(modifier = Modifier.height(padding.medium))
        Row(
            modifier = Modifier
                .padding(vertical = padding.small)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                text = "Price",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.tertiary
            )
            CustomText(
                text = arg?.name ?: "",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = padding.small)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                text = "Details",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.tertiary
            )
            CustomText(
                text = arg?.name ?: "",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = padding.small)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                text = "Validity",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.tertiary
            )
            CustomText(
                text = arg?.name ?: "",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
        Box(
            modifier = Modifier
                .height(padding.lightSmall)
                .padding(vertical = padding.mediumLarge)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSecondary)
        )
        Row(
            modifier = Modifier
                .padding(vertical = padding.small)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                text = "Grand Total",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.tertiary
            )
            CustomText(
                text = arg?.name ?: "",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogBox(
    isDialogVisible: MutableState<Boolean>? = remember { mutableStateOf(false) },
    data: Screen.OrderSummeryScreen? = null,
    onConfirmClick: () -> Unit = {},
    onDismissClick: () -> Unit = {}
) {
    CustomDialog(
        showDialog = isDialogVisible!!,
        message = "Congrats!",
        description = data?.name ?: "",
        confirmText = "Yes",
        dismissText = "No",
        onConfirmClick = {
            onConfirmClick.invoke()
        },
        onDismissClick = {
            onDismissClick.invoke()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TermsAndConditions(
    checked: Boolean? = false,
    onCheckedChange: (Boolean) -> Unit = {}
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked!!,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onSecondary
            )
        )
        CustomText(
            text = "I agree to the",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(end = padding.doubleExtraSmall)
        )
        CustomText(
            text = "Refund Policy, Terms of Service",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
        CustomText(
            text = "and",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = padding.doubleExtraSmall)
        )
        CustomText(
            text = "Privacy Policy.",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}