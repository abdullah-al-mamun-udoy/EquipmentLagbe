package com.metamamun.equipment.preference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.api.client.json.Json
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.metamamun.equipment.data.model.UserProfile
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

const val SESSION_PREF_NAME = "PREF_SESSION_DATA"

@Singleton
class SessionPreference(
    @ApplicationContext context: Context,
    val mSharedPreferences: SharedPreferences,
) {
    @Inject lateinit var json: Json
    var mEditor: SharedPreferences.Editor = mSharedPreferences.edit()
    
    fun getDBVersionByApiName(apiName: String): Int {
        return mSharedPreferences.getInt(apiName, 0)
    }
    
    var isVerifiedUser: Boolean
        get() = mSharedPreferences.getBoolean(PREF_VERIFICATION, false)
        set(isVerified) {
            mSharedPreferences.edit().putBoolean(PREF_VERIFICATION, isVerified).apply()
        }

    var termsAndConditions: String
        get() = mSharedPreferences.getString("termsAndConditions", "") ?: ""
        set(termsAndConditions) {
            mSharedPreferences.edit().putString("termsAndConditions", termsAndConditions).apply()
        }

    var privacyPolicy: String
        get() = mSharedPreferences.getString("privacyPolicy", "") ?: ""
        set(privacyPolicy) {
            mSharedPreferences.edit().putString("privacyPolicy", privacyPolicy).apply()
        }
    
    fun updateDbVersionByApiName(apiName: String) {
        val dbVersion = getDBVersionByApiName(apiName) + 1
        mSharedPreferences.edit().putInt(apiName, dbVersion).apply()
    }
    
    var availableInterestData: List<String?>?
        get() =  run{
            try {
                val jsonString = mSharedPreferences.getString(PREF_AVAILABLE_INTEREST, null) ?: return null
                val type = object : TypeToken<List<String>>() {}.type
                return Gson().fromJson(jsonString, type)
            }catch (_:Exception){
                return@run null
            }
        }
        @SuppressLint("CommitPrefEdits")
        set(resposne) {
            mSharedPreferences.edit().putString(PREF_AVAILABLE_INTEREST, Gson().toJson(resposne)).apply()
        }
    
    var genresData: List<String?>?
        get() =  run{
            try {
                val jsonString = mSharedPreferences.getString(PREF_GENRE, null) ?: return null
                val type = object : TypeToken<List<String>>() {}.type
                return Gson().fromJson(jsonString, type)
            }catch (_:Exception){
                return@run null
            }
        }
        @SuppressLint("CommitPrefEdits")
        set(resposne) {
            mSharedPreferences.edit().putString(PREF_GENRE, Gson().toJson(resposne)).apply()
        }
    
    var genresTvSeries: List<String?>?
        get() =  run{
            try {
                val jsonString = mSharedPreferences.getString(PREF_GENRE_TV_SERIES, null) ?: return null
                val type = object : TypeToken<List<String>>() {}.type
                return Gson().fromJson(jsonString, type)
            }catch (_:Exception){
                return@run null
            }
        }
        @SuppressLint("CommitPrefEdits")
        set(resposne) {
            mSharedPreferences.edit().putString(PREF_GENRE_TV_SERIES, Gson().toJson(resposne)).apply()
        }
    
//    var subCategoryData: List<Subcategory?>?
//        get() =  run{
//            try {
//                val jsonString = mSharedPreferences.getString(PREF_SUB_CATEGORY, null) ?: return null
//                val type = object : TypeToken<List<Subcategory>>() {}.type
//                return Gson().fromJson(jsonString, type)
//            }catch (_:Exception){
//                return@run null
//            }
//        }
//        @SuppressLint("CommitPrefEdits")
//        set(resposne) {
//            mSharedPreferences.edit().putString(PREF_SUB_CATEGORY, Gson().toJson(resposne)).apply()
//        }
    
    var searchTagsData: List<String?>?
        get() =  run{
            try {
                val jsonString = mSharedPreferences.getString(PREF_SEARCH_TAG, null) ?: return null
                val type = object : TypeToken<List<String>>() {}.type
                return Gson().fromJson(jsonString, type)
            }catch (_:Exception){
                return@run null
            }
        }
        @SuppressLint("CommitPrefEdits")
        set(resposne) {
            mSharedPreferences.edit().putString(PREF_SEARCH_TAG, Gson().toJson(resposne)).apply()
        }
    
    var userName: String
        get() = mSharedPreferences.getString(USER_NAME, "") ?: ""
        set(userName) {
            mSharedPreferences.edit().putString(USER_NAME, userName).apply()
        }

    var userEmail: String
        get() = mSharedPreferences.getString(PREF_EMAIL, "") ?: ""
        set(userEmail) {
            mSharedPreferences.edit().putString(PREF_EMAIL, userEmail).apply()
        }
    
    var password: String
        get() = mSharedPreferences.getString(PASSWORD, "") ?: ""
        set(password) {
            mSharedPreferences.edit().putString(PASSWORD, password).apply()
        }
    
    var subscriberPhoto: String
        get() = mSharedPreferences.getString(SUBSCRIBER_PHOTO, "") ?: ""
        set(subscriberPhoto) {
            mSharedPreferences.edit().putString(SUBSCRIBER_PHOTO, subscriberPhoto).apply()
        }
    
    var sex: String
        get() = mSharedPreferences.getString(SEX, "") ?: ""
        set(sex) {
            mSharedPreferences.edit().putString(SEX, sex).apply()
        }

    fun saveUserInformation(userInformation: UserProfile) {
        userInformation.let {
            userName = it.username
            userEmail = it.email
            subscriberPhoto = it.profilePicture
            isVerifiedUser = it.registrationCompleted
        }
    }
    
//    fun saveAppConfiguration(appconfigResponse: AppSettingsResponse){
//        appconfigResponse.data?.let { it ->
//            movieId = it.movieId ?: 0
//            seriesId = it.seriesId ?: 0
//            channels = it.channels ?: 0
//            availableInterestData = it.availableInterestList
//            categoryData = it.categories
//            genresData = it.categories?.find { it?.id == movieId }?.genre
//            genresTvSeries = it.categories?.find { it?.id == seriesId }?.genre
////            genresData = it.categories?.get(0)?.genre
//            yearsData = it.categories?.find { it?.id == movieId }?.years
//            yearsTvSeries = it.categories?.find { it?.id == seriesId }?.years
//            subCategoryData = it.categories?.find { it?.id == movieId }?.subCategories
//            searchTagsData = it.searchTags
//            languages = it.categories?.find { it?.id == movieId }?.language
//            languagesTvSeries = it.categories?.find { it?.id == seriesId }?.language
//            setApiDBVersion(it.apiDbVersionsItemList)
//
//            isUpdateAvailable = it.appVersionConfig?.isUpdateAvailable == 1
//            updateMessage = it.appVersionConfig?.updateMessage ?: ""
//            isUpdateMandatory = it.appVersionConfig?.isUpdateMandatory == 1
//            updateUrl = it.updateUrl ?: ""
//        }
//    }
    
//    fun saveUserInformation(userInformation: UserData) {
//        userInformation.let {
//            isVerifiedUser = it.isVerified ?: 0
//            subscriberId = it.subscriberId ?: 0
//            subscriberIp = it.subscriberIp ?: ""
//            regType = it.regType ?: ""
//            interest = it.interests ?: ""
//            password = it.password ?: ""
//            userName = it.userName ?: ""
//            firstName = it.subscriberProfile?.fname ?: ""
//            lastName = it.subscriberProfile?.lName ?: ""
//            subscriberPhoto = it.subscriberProfile?.photo ?: ""
//            dob = it.subscriberProfile?.dob ?: ""
//            area = it.subscriberProfile?.area ?: ""
//            city = it.subscriberProfile?.city ?: ""
//            isParentalLock = it.subscriberProfile?.parentalLockEnabled ?: 0
//            sex = it.subscriberProfile?.sex ?: ""
//            wishList = it.wishList ?: emptyList()
//        }
//    }

    
    companion object {
        private var instance: SessionPreference? = null
        
        const val APPLICATION_SETTINGS = "application_settings"
        const val PREF_VERIFICATION = "pref_verification"
        const val PREF_EMAIL = "pref_email"
        const val SUBSCRIBER_ID = "pref_subscriber_id"
        const val SUBSCRIBER_IP = "pref_subscriber_ip"
        const val USER_NAME = "pref_user_name"
        const val PASSWORD = "pref_password"
        const val INTEREST = "pref_interest"
        const val FIRST_NAME = "pref_first_name"
        const val LAST_NAME = "pref_last_name"
        const val SUBSCRIBER_PHOTO = "subscriber_photo"
        const val DOB = "pref_dob"
        const val SEX = "pref_sex"
        const val CITY = "pref_city"
        const val AREA = "pref_area"
        const val REG_TYPE = "pref_reg_type"
        const val NOTIFICATION_ENABLE_STATE = "pref_notification_enable_state"
        const val FCM_TOKEN = "pref_fcm_token"
        
        const val PREF_AVAILABLE_INTEREST = "pref_available_interest"
        const val PREF_GENRE = "pref_genre"
        const val PREF_GENRE_TV_SERIES = "pref_genre_tv_series"
        const val PREF_SELECTED_GENRE = "pref_selected_genre"
        const val PREF_YEARS = "pref_years"
        const val PREF_YEARS_TV_SERIES = "pref_years_tv_series"
        const val PREF_CATEGORY = "pref_category"
        const val PREF_SUB_CATEGORY = "pref_sub_category"
        const val PREF_SEARCH_TAG = "pref_search_tag"
        const val PREF_API_DB_VERSIONS = "pref_api_db_versions"
        const val PREF_LANGUAGES = "pref_languages"
        const val PREF_LANGUAGES_TV_SERIES = "pref_languages_tv_series"
        
        const val MOVIE_ID = "pref_movie_id"
        const val SERIES_ID = "pref_series_id"
        const val CHANNELS = "pref_channels"
        
        const val IS_PARENTAL_LOCK = "pref_is_parental_lock"
        
        const val PREF_IS_UPDATE_AVAILABLE = "pref_is_update_available"
        const val PREF_UPDATE_MESSAGE = "pref_update_message"
        const val PREF_IS_UPDATE_MANDATORY = "pref_is_update_mandatory"
        const val PREF_UPDATE_URL = "pref_update_url"
        private const val PREF_heartBeatHttpCallFrequency = "pref_heartBeatHttpCallFrequency"
        
        private const val PREF_WISH_LIST = "pref_wish_list"

        fun init(mContext: Context) {
            if (instance == null) {
                instance = SessionPreference(
                    mContext,
                    mContext.getSharedPreferences(SESSION_PREF_NAME, Context.MODE_PRIVATE)
                )
            }
        }
        
        fun getInstance(): SessionPreference {
            if (instance == null) {
                throw InstantiationException("Instance is null...call init() first")
            }
            return instance as SessionPreference
        }
    }
    
    fun clearAll() {
//        val previousUserName = userName
        mEditor.clear()
        mEditor.apply()
//        userName = previousUserName
    }

}