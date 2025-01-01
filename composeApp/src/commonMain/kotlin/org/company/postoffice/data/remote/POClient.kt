package org.company.postoffice.data.remote

import org.company.postoffice.domain.model.PostOfficeResponse
import org.company.postoffice.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class POClient(private val client: HttpClient) {
    suspend fun getPostOffice(pincode: String): List<PostOfficeResponse> {
        return client.get(Constants.BASE_URL + "pincode/$pincode").body()
    }
}