package com.metamamun.equipment.presentation.screen.home

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metamamun.equipment.R
import com.metamamun.equipment.presentation.composables.AnnotadCustomText
import com.metamamun.equipment.presentation.composables.CustomButton
import com.metamamun.equipment.presentation.composables.CustomImage
import com.metamamun.equipment.presentation.composables.CustomText
import java.lang.Math.ceil


@Preview
@Composable
fun HomeInfoCard(
    progress: MutableState<Float> = mutableFloatStateOf(0.0f),
    calories: MutableState<Float> = mutableFloatStateOf(0.0f),
) {

    val roundedCalories = ceil(calories.value.toDouble()).toInt()

    Card(
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.white)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp, vertical = 0.dp)
        ) {
            CustomText(
                text = "Calories",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(R.color.black),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
            CustomText(
                text = "Remaining = Goal - Food + Exercise",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(R.color.app_color),
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            ) {
                Box(
                    modifier = Modifier
                        .weight(.6f)
                        .fillMaxHeight(),
                    Alignment.Center
                ) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(110.dp),
                        color = colorResource(R.color.app_color),
                        backgroundColor = colorResource(R.color.grey),
                        progress = progress.value,
                        strokeWidth = 8.dp,
                    )
                    AnnotadCustomText(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            ) {
                                append("$roundedCalories")
                            }
                            append("\nRemaining")
                        },
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.black),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                            .fillMaxWidth(),
                    )
//                    CustomText(
//                        text = "${roundedCalories}\nremaining",
//                        style = MaterialTheme.typography.titleSmall,
//                        fontWeight = FontWeight.Bold,
//                        color = colorResource(R.color.app_color),
//                        modifier = Modifier
//                            .padding(vertical = 2.dp)
//                            .fillMaxWidth(),
//                        textAlign = TextAlign.Center
//                    )
                }
                Column(
                    modifier = Modifier
                        .weight(.4f)
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomImage(
                            imageId = R.drawable.ic_goal,
                            contentDescription = "Sample Image",
                            modifier = Modifier
                                .weight(0.3f)
                                .size(20.dp)
                        )
                        AnnotadCustomText(
                            text = buildAnnotatedString {
                                append("Base Goal\n")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("$roundedCalories")
                                }
                            },
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black,
                            modifier = Modifier
                                .weight(0.7f)
                                .padding(start = 8.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomImage(
                            imageId = R.drawable.ic_food,
                            contentDescription = "Sample Image",
                            modifier = Modifier
                                .weight(0.3f)
                                .size(20.dp)
                        )
                        CustomText(
                            text = "Food\n${0}",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black,
                            modifier = Modifier
                                .weight(0.7f)
                                .padding(start = 8.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomImage(
                            imageId = R.drawable.ic_exercise,
                            contentDescription = "Sample Image",
                            modifier = Modifier
                                .weight(0.3f)
                                .size(20.dp)
                        )
                        CustomText(
                            text = "Exercise\n${0}",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black,
                            modifier = Modifier
                                .weight(0.7f)
                                .padding(start = 8.dp)
                        )
                    }


                }


            }
        }
    }


}

@Preview
@Composable
fun StepCount(
    onClick: () -> Unit = {},
    stepProgress: MutableState<Float> = mutableFloatStateOf(0.0f),
    stepCount: State<Int>,
) {
    val buttonText = remember { mutableStateOf("Start") }

    Card(
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.white)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        ) {
            Box(
                modifier = Modifier
                    .weight(.6f)
                    .fillMaxHeight(),
                Alignment.BottomCenter
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(140.dp)
                        .align(alignment = Alignment.Center)
                        .background(
                            color = colorResource(R.color.app_color_light).copy(alpha = 0.7f),
                            shape = CircleShape
                        ),
                    color = colorResource(R.color.hoirzontal_divider_color),
                    backgroundColor = colorResource(R.color.grey),
                    progress = stepProgress.value,
                    strokeWidth = 8.dp,

                    )
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {

                    CustomImage(
                        imageId = R.drawable.ic_footprint,
                        modifier = Modifier
                            .size(48.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                    AnnotadCustomText(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            ) {
                                append("${stepCount.value}")
                            }
                            append("\nSteps")
                        },
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.black),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 2.dp, bottom = 20.dp)
                            .fillMaxWidth(),
                    )
                }


            }
            Column(
                modifier = Modifier
                    .weight(.4f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CustomButton(
                        buttonTextColor = colorResource(R.color.white),
                        buttonText = buttonText.value,
                        showIcon = false,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .width(80.dp)
                            .wrapContentHeight()
                            .background(
                                color = colorResource(R.color.go_premium).copy(alpha = .7f),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .clickable {
                                onClick.invoke()
                                if (buttonText.value == "Start") buttonText.value =
                                    "Stop" else buttonText.value = "Start"
                            },

                        )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomImage(
                        imageId = R.drawable.ic_calories,
                        contentDescription = "Sample Image",
                        modifier = Modifier
                            .weight(0.3f)
                            .size(20.dp)
                    )
                    CustomText(
                        text = "Calorie Burn\n${"%.2f".format((stepCount.value) * 0.05)} Kcal",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(0.7f)
                            .padding(start = 8.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomImage(
                        imageId = R.drawable.ic_exercise,
                        contentDescription = "Sample Image",
                        modifier = Modifier
                            .weight(0.3f)
                            .size(20.dp)
                    )
                    CustomText(
                        text = "Exercise\n${0}",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(0.7f)
                            .padding(start = 8.dp)
                    )
                }


            }


        }

    }


}

@Composable
fun StepDetector(sensorManager: SensorManager, viewModel: HomeScreenViewModel) {
    val stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

    val sensorEventListener = remember {
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event?.sensor?.type == Sensor.TYPE_STEP_DETECTOR) {
                    Log.d("StepDetector", "Step detected: ${event.values?.get(0) ?: "Unknown"}")
                    viewModel.incrementStepCount()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                Log.d("StepDetector", "Sensor accuracy changed: $accuracy")
            }
        }
    }

    DisposableEffect(Unit) {
        if (stepDetectorSensor != null) {
            Log.d("StepDetector", "Registering step detector sensor")
            sensorManager.registerListener(
                sensorEventListener,
                stepDetectorSensor,
                SensorManager.SENSOR_DELAY_UI
            )
        } else {
            Log.e("StepDetector", "Step detector sensor not available on this device")
        }

        onDispose {
            Log.d("StepDetector", "Unregistering step detector sensor")
            sensorManager.unregisterListener(sensorEventListener)
        }
    }
}
