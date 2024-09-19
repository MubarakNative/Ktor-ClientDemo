package com.mubarak.ktorclientdemo.data.sources.remote

import com.mubarak.ktorclientdemo.data.sources.remote.dto.Posts
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path

class PostApiImpl(
    private val client: HttpClient // helps to make http request and handle responses
) : PostsApi {
    override suspend fun getPosts(): List<Posts> {
           return client.get {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = "jsonplaceholder.typicode.com"
                        path("/posts")
                    }
        }.body()
    }
}
