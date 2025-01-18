import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.metamamun.equipment.R


import com.metamamun.equipment.presentation.composables.CustomButton
import com.metamamun.equipment.presentation.composables.CustomText
import com.metamamun.equipment.presentation.screen.home.HomeInfoCard
import com.metamamun.equipment.presentation.screen.home.HomeScreenViewModel
import com.metamamun.equipment.presentation.screen.home.StepCount
import com.metamamun.equipment.presentation.screen.home.StepDetector
import kotlinx.coroutines.delay


@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("CoroutineCreationDuringComposition")
@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        val progress = remember { mutableFloatStateOf(0.0f) }
        val stepProgress = remember { mutableStateOf(0.0f) }
        val calories = remember { mutableStateOf(2680.0f) }
        val remainCalories = remember { mutableStateOf(2680.0f) }
        val context = LocalContext.current
        val sensorManager = context.getSystemService(SensorManager::class.java)
        val permissionGranted = remember { mutableStateOf(false) }
        val viewModel: HomeScreenViewModel = hiltViewModel()
        val stepCount = viewModel.stepCount.collectAsState(0)
        val stepCountStart = viewModel.stepCountStart.collectAsState()


        val requestActivityRecognitionPermission =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                permissionGranted.value = isGranted
                if (isGranted) {
                    Log.d("StepDetector", "Activity Recognition Permission Granted")
                } else {
                    Log.e("StepDetector", "Activity Recognition Permission Denied")
                }
            }

        LaunchedEffect(Unit) {
            while (progress.value < 1.0f) {
                progress.value += 0.01f
                remainCalories.value -= calories.value / 100
                if(remainCalories.value<0.0) remainCalories.value = 0f
                delay(1000)
            }
        }


        LaunchedEffect(Unit) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACTIVITY_RECOGNITION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("StepDetector", "Activity Recognition Permission Granted123")

                permissionGranted.value = true
            } else {
                Log.d("StepDetector", "Activity Recognition Permission failed")

                requestActivityRecognitionPermission.launch(android.Manifest.permission.ACTIVITY_RECOGNITION)
            }
        }

        Log.d("StepDetector", "step count pressed ${stepCountStart.value} and permission granted ${permissionGranted.value}")

        if (stepCountStart.value && permissionGranted.value) {
            Log.d("StepDetector", "Starting StepDetector")
            StepDetector(sensorManager, viewModel)
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(
                text = "Today",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(R.color.black),
                fontWeight = FontWeight.Bold,
            )
            CustomButton(
                buttonTextColor = colorResource(R.color.white),
                buttonText = "Try Premium",
                style = MaterialTheme.typography.displayLarge,
                showIcon = false,
                modifier = Modifier
                    .width(100.dp)
                    .height(24.dp)
                    .background(
                        color = colorResource(R.color.go_premium).copy(alpha = .7f),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .clickable {
                    },

                )
        }

        HomeInfoCard(progress, remainCalories)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color = Color.Yellow)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(colorResource(R.color.white)),
        ) {
            CustomText(
                text = "for custom adds",
            )

        }
        Spacer(modifier = Modifier.height(16.dp))

        StepCount(
            stepProgress = stepProgress,
            stepCount = stepCount,
            onClick = {
                viewModel.toggleStepCountStart()
            },
        )
    }
}



