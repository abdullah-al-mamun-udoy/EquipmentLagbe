package com.metamamun.equipment.presentation.screen.home

//import com.lazydeveloper.fit4u2.paging.BaseListRepositoryImpl
//import com.lazydeveloper.fit4u2.paging.BaseNetworkPagingSource
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
//    private val bannerVideosServiceFactory: BannerVideosService.AssistedFactory,
//    private val liveVideosServiceFactory: LiveVideosService.AssistedFactory,
): ViewModel() {

    private val _stepCountStart = MutableStateFlow(false)
    val stepCountStart = _stepCountStart.asStateFlow()

    private val _stepCount = MutableStateFlow(0)
    val stepCount: StateFlow<Int> get() = _stepCount

    fun toggleStepCountStart() {
        _stepCountStart.value = !_stepCountStart.value
        Log.d("stepDetector", "step count in viewmodel ${_stepCountStart.value}")
    }
    fun incrementStepCount() {
        Log.d("stepDetector", "step count in viewmodel ${_stepCount.value}")
        _stepCount.value += 1
    }


}