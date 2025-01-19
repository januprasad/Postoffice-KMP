package com.example.network.data.repo

import com.example.network.domain.model.PostOfficeResponse

interface PostOfficeApi {
    suspend fun getPostOffices(pincode: String): List<PostOfficeResponse>
}