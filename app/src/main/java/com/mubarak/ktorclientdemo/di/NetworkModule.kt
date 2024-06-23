package com.mubarak.ktorclientdemo.di

import com.mubarak.ktorclientdemo.data.sources.remote.PostApiImpl
import com.mubarak.ktorclientdemo.data.sources.remote.PostsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient():HttpClient{
        return HttpClient(Android){
            install(ContentNegotiation){
                json()
            }
            install(Logging){
                level = LogLevel.ALL
            }
        }
    }

    @Singleton
    @Provides
    fun providePostApi(client: HttpClient): PostsApi {
        return PostApiImpl(
            client
        )
    }
}