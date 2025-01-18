package com.metamamun.equipment.presentation.screen.myplan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.metamamun.equipment.R
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.presentation.composables.CustomText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun MyPlanScreen(
    navController: NavController?,
    viewModel: MyPlanViewModel = viewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val plansList by viewModel.plansState.collectAsState()

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        if (plansList.isNotEmpty()) {
            LazyColumn {
                item {
                    CustomText(
                        text = "Available Plans",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.black),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
                    )
                }
                items(plansList.size) { index ->
                    PlanCard(
                        plan = plansList[index],
                        onClick = {
                            val planDataDTO = plansList[index]
                            val item = Json.encodeToString(planDataDTO) // Serialize the object
                            navController?.navigate(Screen.PlanDetailsScreen(item = item))
                        })
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(90.dp))
                }
            }
        } else {
            CustomText(
                text = "No plans found",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPlanScreenPreview() {
    MyPlanScreen(null)
}


