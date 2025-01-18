package com.metamamun.equipment.presentation.screen.plandetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.metamamun.equipment.R
import com.metamamun.equipment.navigation.Screen
import com.metamamun.equipment.presentation.composables.CustomButton
import com.metamamun.equipment.presentation.composables.CustomImage
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.presentation.screen.myplan.PlanDataDTO
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import timber.log.Timber


@Preview
@Composable
fun PlanDetailsScreen(
    navController: NavController? = null,
    arg: Screen.PlanDetailsScreen? = null
) {

    val tabs = listOf("Available Plans", "Schedule")
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    var selectedTabIndex = pagerState.currentPage

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Tabs
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = colorResource(R.color.white),
            contentColor = colorResource(R.color.app_color),
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.Indicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = colorResource(R.color.app_color)
                    )
                }
            },
            divider = {
                HorizontalDivider(color = colorResource(R.color.white)) // Custom divider color
            },
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> AvailablePlanDetailsScreen(navController = navController, arg = arg)
                1 -> ScheduleScreen(arg = arg)
            }
        }

    }

}

@Preview
@Composable
fun AvailablePlanDetailsScreen(
    navController: NavController? = null,
    arg: Screen.PlanDetailsScreen? = null
) {
    val planDataDTO = arg?.item?.let {
        try {
            // Deserialize the item into PlanDataDTO
            Json.decodeFromString<PlanDataDTO>(it)
        } catch (e: Exception) {
            Log.e("PlanDetailsScreen", "Error decoding PlanDataDTO: ${e.message}")
            null
        }
    }
    Log.d("PlanDetailsScreen", "Decoded PlanDataDTO: $planDataDTO.")
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                ) {
                    CustomImage(
                        imageId = planDataDTO?.imageResId?.toInt() ?: 0,
                        contentDescription = "My Plan Header",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )
                }
            }

            item {
//                CustomText(
//                    text = planDataDTO?.header ?: "",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 8.dp),
//                    style = MaterialTheme.typography.titleLarge,
//                )
                Text(
                    text = planDataDTO?.header ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp,
                    color = colorResource(R.color.black),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            /*            item {
                            CustomText(
                                text = "Overview",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }

                        item {
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp),
                                color = colorResource(R.color.light_blue)
                            )
                        }*/

            item {
                CustomText(
                    text = planDataDTO?.overView ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(R.color.black),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                )
//                Text(
//                    text = planDataDTO?.overView ?: "",
//                    style = MaterialTheme.typography.titleLarge,
//                    fontSize = 20.sp,
//                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomText(
                        text = "Duration",
                        color = colorResource(R.color.black),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    CustomText(
                        text = planDataDTO?.duration ?: "",
                        color = colorResource(R.color.black),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomText(
                        text = "Times Per Week",
                        color = colorResource(R.color.black),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    CustomText(
                        text = planDataDTO?.timesPerWeek ?: "",
                        color = colorResource(R.color.black),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomText(
                        text = stringResource(R.string.difficulty_level),
                        color = colorResource(R.color.black),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    CustomText(
                        text = planDataDTO?.difficulty ?: "",
                        color = colorResource(R.color.black),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    // Main heading text
                    Text(
                        text = planDataDTO?.whyWillYouChoose ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(R.color.black),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = """ ${planDataDTO?.whyWillYouChooseDetails} """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(R.color.black),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                }
            }


            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    // Main heading text
                    Text(
                        text = planDataDTO?.whatYouWillDo ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(R.color.black),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "${planDataDTO?.whatYouWillDoDetails}".trimIndent(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(R.color.black),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    // Main heading text
                    Text(
                        text = planDataDTO?.guideline ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(R.color.black),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = """
            ${planDataDTO?.guidelineDetails}
        """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(R.color.black),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }

            item {
                Spacer(Modifier.height(120.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(color = Color.Transparent)
                .align(Alignment.BottomCenter),
        ) {
            CustomButton(
                buttonTextColor = colorResource(R.color.white),
                buttonText = "Purchase Pack",
                showIcon = false,
                modifier = Modifier
                    .width(180.dp)
                    .height(40.dp)
                    .background(
                        color = colorResource(R.color.hoirzontal_divider_color).copy(alpha = .7f),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .align(Alignment.TopCenter),


                )
        }


    }

}

@Preview
@Composable
fun ScheduleScreen(arg: Screen.PlanDetailsScreen?) {
    val planDataDTO = arg?.item?.let {
        try {
            // Deserialize the item into PlanDataDTO
            Json.decodeFromString<PlanDataDTO>(it)
        } catch (e: Exception) {
            Timber.tag("PlanDetailsScreen").e("Error decoding PlanDataDTO: ${e.message}")
            null
        }
    }
    val howManyWeeksShouldVisible = planDataDTO?.duration
        ?.substringBefore(" ")
        ?.toIntOrNull()
        ?.div(7)
        ?: 0

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        planDataDTO?.let { data->
            for (week in 1..howManyWeeksShouldVisible) {
                item {
                    WeekSection(
                        title = "Week $week",
                        data = when (week) {
                            1 -> data.firstWeek
                            2 -> data.secondWeek
                            3 -> data.thirdWeek
                            4 -> data.fourthWeek
                            else -> null
                        }
                    )
                }
            }
        }
        item {
            Spacer(Modifier.height(80.dp))
        }
    }

}

@Composable
fun WeekSection(title: String, data: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.black),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        data?.split("\n")?.forEach { dayPlan ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            ) {
                Text(
                    text = dayPlan.substringBefore(":").trim(), // Extract Day
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.black),
                    modifier = Modifier.weight(1f) // Align day to the left
                )
                Text(
                    text = dayPlan.substringAfter(":").trim(), // Extract Plan
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.black),
                    modifier = Modifier.weight(3f) // Align plan to the right
                )
            }
        }
    }
}







