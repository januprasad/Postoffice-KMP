package org.company.postoffice.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.company.postoffice.data.remote.POClient
import org.company.postoffice.domain.repository.Repository
import org.company.postoffice.utils.Constants
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.company.postoffice.viewmodel.MainViewModel

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                filter { filter-> filter.url.host.contains("coinmarketcap.com") }
                sanitizeHeader { header-> header == HttpHeaders.Authorization }
            }
            install(HttpTimeout) {
                requestTimeoutMillis = Constants.TIME_OUT
                connectTimeoutMillis = Constants.TIME_OUT
                socketTimeoutMillis = Constants.TIME_OUT
            }
            /*defaultRequest {
                headers {
                    append("X-CMC_PRO_API_KEY", Constant.API_KEY)
                }
            }*/
        }
    }
    single { POClient(get()) }
    single {
        Repository(get())
    }
    singleOf(::MainViewModel)
}