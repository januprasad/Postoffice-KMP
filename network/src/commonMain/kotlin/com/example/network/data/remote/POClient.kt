package com.example.network.data.remote

import com.example.network.domain.model.PostOfficeResponse
import com.example.network.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class POClient(private val client: HttpClient) {
    suspend fun getPostOffice(pincode: String): List<PostOfficeResponse> {
        return client.get(Constants.BASE_URL + "pincode/$pincode").body()
    }
}