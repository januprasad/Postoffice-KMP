package org.company.postoffice.domain.repository

import org.company.postoffice.data.remote.POClient
import org.company.postoffice.data.repo.PostOfficeApi
import org.company.postoffice.domain.model.PostOfficeResponse


class Repository(
    private val poClient: POClient
): PostOfficeApi {
    override suspend fun getPostOffices(pincode: String): List<PostOfficeResponse> {
        return poClient.getPostOffice(pincode)
    }
}