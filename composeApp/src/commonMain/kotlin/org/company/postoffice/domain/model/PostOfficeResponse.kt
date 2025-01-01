package org.company.postoffice.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PostOfficeResponse(
    val Message: String,
    val PostOffice: List<PostOffice>? = null,
    val Status: String
)