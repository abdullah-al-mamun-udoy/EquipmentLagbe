package com.metamamun.equipment

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MainActivityViewModel  : ViewModel() {

    private var _stepCountStart = true
    val stepCountStart: Boolean get() = _stepCountStart

    private val _stepCount = MutableStateFlow(0)
    val stepCount: StateFlow<Int> get() = _stepCount

    fun toggleStepCountStart() {
        _stepCountStart = !_stepCountStart
        Log.d("stepCount", "step count in viewmodel $_stepCountStart")
    }
    fun incrementStepCount() {
        Log.d("stepCount", "step count in viewmodel ${_stepCount.value}")
        _stepCount.value += 1
    }
}