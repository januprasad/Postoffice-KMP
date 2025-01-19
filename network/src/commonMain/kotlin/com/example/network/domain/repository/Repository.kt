package com.example.network.domain.repository

import com.example.network.data.remote.POClient
import com.example.network.data.repo.PostOfficeApi
import com.example.network.domain.model.PostOfficeResponse


class Repository(
    private val poClient: POClient
): PostOfficeApi {
    override suspend fun getPostOffices(pincode: String): List<PostOfficeResponse> {
        return poClient.getPostOffice(pincode)
    }
}