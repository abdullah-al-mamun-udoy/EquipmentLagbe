package com.metamamun.equipment.di

import android.content.Context
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Precision
import coil.size.Size
import com.metamamun.equipment.R


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    
    @Provides
    @Singleton
    fun providesImageRequest(@ApplicationContext context: Context): ImageRequest {
        return ImageRequest.Builder(context).apply {
            dispatcher(Dispatchers.IO)
            size(Size.ORIGINAL)
            crossfade(false)
            allowHardware(false)
            diskCachePolicy(CachePolicy.ENABLED)
            networkCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.DISABLED)
            precision(Precision.INEXACT)
            error(R.drawable.ic_launcher_foreground)
            fallback(R.drawable.ic_launcher_foreground)
            placeholder(R.drawable.ic_launcher_foreground)
        }.build()
    }
}