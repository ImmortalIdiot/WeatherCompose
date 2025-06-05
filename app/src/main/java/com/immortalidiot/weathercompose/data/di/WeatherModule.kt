package com.immortalidiot.weathercompose.data.di

import android.content.Context
import com.immortalidiot.weathercompose.BuildConfig
import com.immortalidiot.weathercompose.data.location.LocationService
import com.immortalidiot.weathercompose.data.network.WeatherAPI
import com.immortalidiot.weathercompose.data.repository.WeatherRepositoryImpl
import com.immortalidiot.weathercompose.domain.repository.LocationProvider
import com.immortalidiot.weathercompose.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    fun provideWeatherApi(): WeatherAPI {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    fun provideWeatherRepository(api: WeatherAPI): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }

    @Provides
    fun provideLocationService(@ApplicationContext context: Context): LocationProvider {
        return LocationService(context)
    }
}
