package com.prabhatkushwaha.koinproject.di

import com.prabhatkushwaha.koinproject.remote.service.PostService
import com.prabhatkushwaha.koinproject.remote.service.PostServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }
    single<PostService> {
        PostServiceImpl(get())
    }
}