package com.metamamun.equipment.presentation.screen.signin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
//    private val verifyCode: VerifyCode,
//    private val loginByPhone: LoginByPhoneOrEmail,
//    private val sendLoginLogEvent: SendLoginLogEvent,
) : ViewModel() {
//
//    val verifyResponse = SingleLiveEvent<Resource<CustomerInfoLogin>>()
//    val resendCodeResponse = SingleLiveEvent<Resource<String>>()
//
//    fun verifyCode(code: String, isSkipUpdate: Boolean = false) {
//        viewModelScope.launch {
//            val response = resultFromResponse { verifyCode.execute(code, isSkipUpdate) }
//            verifyResponse.value = response
//        }
//    }
//
//    fun resendCode(phoneNumber: String, referralCode: String) {
//        viewModelScope.launch {
//            val response = resultFromResponse { loginByPhone.execute(phoneNumber, RegistrationMethod.MSISDN.value) }
//            resendCodeResponse.value = response
//        }
//    }
//
//    fun sendLoginLogData() {
//        viewModelScope.launch {
//              sendLoginLogEvent.execute()
//        }
//    }
}