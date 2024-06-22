package com.mubarak.ktorclientdemo.data.sources.remote

import com.mubarak.ktorclientdemo.data.sources.remote.dto.Posts
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

interface PostsApi {
    suspend fun getPosts(): List<Posts>

    companion object{
        fun create(): PostsApi {
            return PostApiImpl(
                HttpClient(Android){
                    install(ContentNegotiation){
                        json()
                    }
                    install(Logging){
                        level = LogLevel.ALL
                    }

                }
            )
        }
    }
}