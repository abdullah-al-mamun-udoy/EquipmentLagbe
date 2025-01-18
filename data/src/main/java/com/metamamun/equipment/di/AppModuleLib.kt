package com.metamamun.equipment.di

import android.content.Context
import android.content.SharedPreferences
import com.metamamun.equipment.preference.SESSION_PREF_NAME
import com.metamamun.equipment.preference.SessionPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModuleLib {
    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context.applicationContext
    }
    
    @Provides
    @Singleton
    @AppCoroutineScope
    fun providesApplicationCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }
    
    @Provides
    @SessionSharedPreference
    fun providesSessionSharedPreference(@ApplicationContext app: Context): SharedPreferences {
        return app.getSharedPreferences(SESSION_PREF_NAME, Context.MODE_PRIVATE)
    }
    
    @Provides
    @Singleton
    fun providesPreference(
        @SessionSharedPreference pref: SharedPreferences,
        @ApplicationContext ctx: Context
    ): SessionPreference {
        return SessionPreference(ctx, pref)
    }
}