package com.metamamun.equipment.presentation.screen.myplan

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metamamun.equipment.R
import com.metamamun.equipment.presentation.composables.CustomImage
import com.metamamun.equipment.presentation.composables.CustomText
import kotlinx.serialization.Serializable

@Serializable
data class PlanDataDTO(
    val id: String,
    val header: String,
    val duration: String,
    val timesPerWeek: String,
    val difficulty: String,
    val overView: String,
    val whyWillYouChoose: String,
    val whyWillYouChooseDetails: String,
    val whatYouWillDo: String,
    val whatYouWillDoDetails: String,
    val guideline: String,
    val guidelineDetails: String,
    val imageResId: String,
    val firstWeek: String,
    val secondWeek: String,
    val thirdWeek: String,
    val fourthWeek: String,
)

data class PlanData(
    val id: Int,
    val imageResId: Int = R.drawable.myplan_header,
    val header: String = "Eating for Impact",
    val duration: String = "7 Days",
    val timesPerWeek: String = "Daily",
    val difficulty: String = "beginner",
    val overView: String = "You will get benefitted",
    val whyWillYouChoose: String = "Why will you choose",
    val whyWillYouChooseDetails: String = "• Excellent value for money with comprehensive features that ensure you get the most out of your investment.\n" +
            "• Reliable customer support available 24/7, providing assistance whenever you need it without any delays.\n" +
            "• Tailored to meet your specific needs and preferences, offering a personalized experience that caters to your unique requirements, ensuring satisfaction and value at every step.",

    val whatYouWillDo: String = "What you will do",
    val whatYouWillDoDetails: String = " • Gain hands-on experience with real-world applications that are relevant to your field of interest.\n" +
            " • Collaborate with experts and team members to solve complex challenges, improving both technical and interpersonal skills.\n" +
            " • Participate in innovative projects that push the boundaries of your knowledge and creativity, ensuring you stay ahead in your industry.",

    val guideline: String = "Guideline",
    val guidelineDetails: String = "• Follow all the provided instructions carefully to ensure a smooth and successful execution of the plan.\n" +
            "            • Maintain clear and consistent communication with all relevant stakeholders to avoid any misunderstandings.\n" +
            "            • Regularly monitor progress and make necessary adjustments based on feedback or unforeseen challenges.\n" +
            "            • Ensure compliance with all applicable rules, regulations, and ethical standards throughout the process.",

    val firstWeek: String = """
    Monday: 30 mins jogging, 15 mins strength training (push-ups, squats, planks)
    Tuesday: 20 mins cycling, 10 mins stretching (yoga poses)
    Wednesday: 40 mins brisk walking, 15 mins core workout (crunches, leg raises)
    Thursday: 30 mins HIIT (jumping jacks, burpees, high knees)
    Friday: 20 mins swimming or low-impact cardio, 15 mins balance training (yoga)
    Saturday: 45 mins hiking or outdoor activity
    Sunday: Rest day with light stretching
""".trimIndent(),

    val secondWeek: String = """
    Monday: 15 mins warm-up, 30 mins strength training (dumbbell exercises)
    Tuesday: 25 mins running or treadmill workout, 10 mins yoga
    Wednesday: 40 mins brisk walking, 10 mins meditation
    Thursday: 20 mins cycling, 20 mins bodyweight exercises (lunges, pull-ups)
    Friday: 30 mins swimming, 15 mins stretching
    Saturday: 45 mins group fitness class or sports activity
    Sunday: Rest day or light yoga (focus on flexibility)
""".trimIndent(),

    val thirdWeek: String = """
    Monday: 20 mins jogging, 20 mins strength training (focus on arms and back)
    Tuesday: 30 mins HIIT (burpees, mountain climbers, jump squats)
    Wednesday: 15 mins core workout (planks, Russian twists), 30 mins brisk walking
    Thursday: 20 mins cycling, 10 mins yoga stretches
    Friday: 20 mins swimming, 20 mins full-body workout
    Saturday: Outdoor sports (tennis, basketball) or 1-hour nature walk
    Sunday: Light stretching, rest or restorative yoga
""".trimIndent(),

    val fourthWeek: String = """
    Monday: 25 mins jogging, 20 mins lower body workout (lunges, squats)
    Tuesday: 30 mins cycling, 10 mins mindfulness or meditation
    Wednesday: 30 mins brisk walking, 15 mins core workout
    Thursday: 20 mins HIIT (mix of strength and cardio)
    Friday: 20 mins swimming, 15 mins stretching (hamstring and back focus)
    Saturday: Outdoor adventure (kayaking, hiking) or group fitness activity
    Sunday: Rest day with light stretching or foam rolling
""".trimIndent()
) {
    // Convert PlanData to PlanDataDTO
    fun toDTO(): PlanDataDTO = PlanDataDTO(
        header = header,
        duration = duration,
        timesPerWeek = timesPerWeek,
        difficulty = difficulty,
        overView = overView,
        whyWillYouChoose = whyWillYouChoose,
        whatYouWillDo = whatYouWillDo,
        guideline = guideline,
        id = id.toString(),
        imageResId = imageResId.toString(),
        whyWillYouChooseDetails = whyWillYouChooseDetails,
        whatYouWillDoDetails = whatYouWillDoDetails,
        guidelineDetails = guidelineDetails,
        firstWeek = firstWeek,
        secondWeek = secondWeek,
        thirdWeek = thirdWeek,
        fourthWeek = fourthWeek
    )
}


val plan1 = PlanData(
    header = "Weight Loss Plan",
    duration = "30 Days",
    timesPerWeek = "Twice Daily",
    imageResId = R.drawable.myplan_header,
    id = 0,
    firstWeek = "",
    secondWeek = "",
    thirdWeek = "",
    fourthWeek = "",
)

@Preview
@Composable
fun PlanCard(
    plan: PlanDataDTO,
    onClick: () -> Unit = {}
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable {
                onClick.invoke()
            },
        colors = CardDefaults.cardColors(colorResource(R.color.white)),
        shape = RoundedCornerShape(8.dp)
    ) {
        CustomImage(
            imageId = R.drawable.myplan_header,
            contentDescription = "My Plan Header",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )

        CustomText(
            text = plan?.header!!,
            color = colorResource(R.color.black),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = plan?.duration!!,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.grey),
            )
            Image(
                painterResource(R.drawable.ic_bulletpoint),
                contentDescription = "Bullet Point",
                modifier = Modifier.size(6.dp),
                colorFilter = ColorFilter.tint(colorResource(R.color.grey))
            )
            Text(
                text = plan?.timesPerWeek!!,
                fontSize = 14.sp,
                color = Color.Gray,
            )

        }


    }


}