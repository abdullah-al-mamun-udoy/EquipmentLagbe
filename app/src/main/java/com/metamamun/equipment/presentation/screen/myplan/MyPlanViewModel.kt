package com.metamamun.equipment.presentation.screen.myplan

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyPlanViewModel : ViewModel() {
    private val _plansState = MutableStateFlow<List<PlanDataDTO>>(emptyList())
    val plansState: StateFlow<List<PlanDataDTO>> = _plansState

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchPlans()
    }

    private fun fetchPlans() {
        FirebaseFirestore.getInstance()
            .collection("appConfig")
            .document("plans")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val users = document["plans"] as? List<Map<String, Any>>
                    val userProfiles = users?.map { userMap ->
                        PlanDataDTO(
                            id = userMap["id"] as? String ?: "",
                            header = userMap["header"] as? String ?: "",
                            duration = userMap["duration"] as? String ?: "",
                            timesPerWeek = userMap["timesPerWeek"] as? String ?: "",
                            difficulty = userMap["difficulty"] as? String ?: "",
                            overView = userMap["overView"] as? String ?: "",
                            whyWillYouChoose = userMap["whyWillYouChoose"] as? String ?: "",
                            whyWillYouChooseDetails = userMap["whyWillYouChooseDetails"] as? String ?: "",
                            whatYouWillDo = userMap["whatYouWillDo"] as? String ?: "",
                            whatYouWillDoDetails = userMap["whatYouWillDoDetails"] as? String ?: "",
                            guideline = userMap["guideline"] as? String ?: "",
                            guidelineDetails = userMap["guidelineDetails"] as? String ?: "",
                            imageResId = userMap["imageResId"] as? String ?: "",
                            firstWeek = userMap["firstWeek"] as? String ?: "",
                            secondWeek = userMap["secondWeek"] as? String ?: "",
                            thirdWeek = userMap["thirdWeek"] as? String ?: "",
                            fourthWeek = userMap["fourthWeek"] as? String ?: ""
                        )
                    } ?: emptyList()
                    _plansState.value = userProfiles
                } else {
                    _plansState.value = emptyList()
                }
                _isLoading.value = false
            }
            .addOnFailureListener {
                _plansState.value = emptyList()
                _isLoading.value = false
            }
    }
}