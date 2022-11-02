package com.prabhatkushwaha.koinproject.remote.service

import com.prabhatkushwaha.koinproject.remote.dto.Post
import com.prabhatkushwaha.koinproject.remote.dto.PostRequest
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl(private val client: HttpClient) : PostService {

    val url = "https://jsonplaceholder.typicode.com"
    val posts = "$url/posts"
    override suspend fun getPost(): List<Post> {
        return try {
            client.get {
                url(posts)
            }
        } catch (exception: Exception) {
            catchExceptions(exception)
            emptyList()
        }
    }

    private fun logErrorResponse(error: String) {
        println("-----------Error-----------")
        println(error)
        println("-----------Error-----------")
    }

    private fun catchExceptions(exception: Exception) {
        when (exception) {
            is RedirectResponseException -> {
                logErrorResponse(exception.localizedMessage)
            }
            is ClientRequestException -> {
                logErrorResponse(exception.localizedMessage)
            }
            is ServerResponseException -> {
                logErrorResponse(exception.localizedMessage)
            }
            else -> {
                logErrorResponse(exception.localizedMessage)
            }
        }
    }

    override suspend fun createPost(poseRequest: PostRequest): Post? {
        return try {
            client.post<Post> {
                url(posts)
                contentType(ContentType.Application.Json)
                body = poseRequest
            }
        } catch (exception: Exception) {
            catchExceptions(exception)
            null
        }
    }
}