package com.metamamun.equipment.presentation.composables

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.metamamun.equipment.R
import com.metamamun.equipment.ui.theme.padding

@Composable
fun CustomDialog(
    showDialog: MutableState<Boolean>? = null,
    message: String? = "null",
    description: String? = "null",
    confirmText: String? = "null",
    dismissText: String? = "null",
    onConfirmClick: () -> Unit = {},
    onDismissClick: () -> Unit = {}
) {
    if (showDialog?.value!!) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            content = {
                DialogBody(
                    message = message,
                    description = description,
                    onConfirmClick = onConfirmClick
                )
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DialogBody(
    message: String? = "Message",
    description: String? = "Description",
    onConfirmClick: () -> Unit = {}
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
            .clip(RoundedCornerShape(padding.small))
            .padding(padding.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomImage(
            imageId = R.drawable.ic_dialog,
            contentDescription = null,
            modifier = Modifier.size(padding.tripleExtraLarge)
        )
        CustomText(
            text = message!!,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = padding.medium)
        )
        CustomText(
            text = description!!,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .padding(horizontal = padding.medium)
        )
        CustomButton(
            buttonText = "GO TO PROFILE",
            showIcon = false,
            modifier = Modifier
                .padding(horizontal = padding.large)
                .padding(bottom = padding.mediumLarge)
                .fillMaxWidth()
                .height(padding.largeMedium)
                .clip(RoundedCornerShape(padding.small))
                .background(MaterialTheme.colorScheme.primary)
                .clickable { onConfirmClick.invoke() },
        )
    }
}