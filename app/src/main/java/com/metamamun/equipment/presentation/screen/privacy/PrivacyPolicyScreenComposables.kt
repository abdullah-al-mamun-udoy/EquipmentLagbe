package com.metamamun.equipment.presentation.screen.privacy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.metamamun.equipment.preference.SessionPreference
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.ui.theme.padding

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyScreenBody(
    onClick: () -> Unit = { },
    preference: SessionPreference? = null
) {
    Column(
        modifier = Modifier
            .padding(horizontal = padding.large)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(padding.small))
        CustomText(
            text = preference?.privacyPolicy ?: "",
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(id = android.R.color.black),
        )
        Spacer(modifier = Modifier.height(padding.extraLarge))
    }
}