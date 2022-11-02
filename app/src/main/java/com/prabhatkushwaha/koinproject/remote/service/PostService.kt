package com.prabhatkushwaha.koinproject.remote.service

import com.prabhatkushwaha.koinproject.remote.dto.Post
import com.prabhatkushwaha.koinproject.remote.dto.PostRequest

interface PostService {

    suspend fun getPost(): List<Post>

    suspend fun createPost(poseRequest: PostRequest): Post?
}