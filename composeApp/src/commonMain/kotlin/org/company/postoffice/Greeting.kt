package org.company.postoffice

import com.example.network.ApiService

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}! with version ${ApiService.getApiVersion()}"
    }
}