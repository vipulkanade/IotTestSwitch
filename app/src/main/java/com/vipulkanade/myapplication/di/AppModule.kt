package com.vipulkanade.myapplication.di

import com.vipulkanade.myapplication.BuildConfig
import com.vipulkanade.myapplication.repository.AppSwitchRepository
import com.vipulkanade.myapplication.repository.PhysicalSwitchRepository
import com.vipulkanade.myapplication.repository.SwitchRepository
import com.vipulkanade.myapplication.repository.SwitchRepositoryImpl
import com.vipulkanade.myapplication.repository.SwitchService
import com.vipulkanade.myapplication.usecase.UpdateAppSwitchUseCase
import com.vipulkanade.myapplication.usecase.UpdatePhysicalSwitchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://example.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideSwitchApi(retrofit: Retrofit): SwitchService {
        return retrofit.create(SwitchService::class.java)
    }

    @Provides
    @Singleton
    fun provideSwitchRepository(switchService: SwitchService): SwitchRepository {
        return SwitchRepositoryImpl(switchService)
    }

    @Provides
    @Singleton
    fun providePhysicalSwitchRepository(): PhysicalSwitchRepository {
        return PhysicalSwitchRepository()
    }

    @Provides
    fun provideUpdatePhysicalSwitchUseCase(repository: PhysicalSwitchRepository): UpdatePhysicalSwitchUseCase {
        return UpdatePhysicalSwitchUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAppSwitchRepository(): AppSwitchRepository {
        return AppSwitchRepository()
    }

    @Provides
    fun provideUpdateAppSwitchUseCase(repository: AppSwitchRepository): UpdateAppSwitchUseCase {
        return UpdateAppSwitchUseCase(repository)
    }
}