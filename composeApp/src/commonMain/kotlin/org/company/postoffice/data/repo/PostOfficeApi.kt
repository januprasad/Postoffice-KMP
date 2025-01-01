package org.company.postoffice.data.repo

import org.company.postoffice.domain.model.PostOfficeResponse

interface PostOfficeApi {
    suspend fun getPostOffices(pincode: String): List<PostOfficeResponse>
}