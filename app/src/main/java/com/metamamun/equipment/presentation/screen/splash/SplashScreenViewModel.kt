package com.metamamun.equipment.presentation.screen.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
) : ViewModel() {

    init {
//        getAppConfig()
    }

//    private val _appConfiguration = MutableStateFlow<Resource<AppConfigResponse?>>(Resource.Loading)
//    val appConfiguration = _appConfiguration.asStateFlow()
//
//    private fun getAppConfig(isSkipUpdate: Boolean = false){
//        viewModelScope.launch {
//            val response = resultFromResponse {
//                applicationConfigurationService.loadData(isSkipUpdate)
//            }
//            _appConfiguration.value = response
//        }
//    }
}