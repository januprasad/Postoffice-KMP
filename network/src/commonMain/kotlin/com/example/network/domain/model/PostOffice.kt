package com.example.network.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PostOffice(
    val BranchType: String?,
    val Circle: String?,
    val Country: String?,
    val DeliveryStatus: String?,
    val Description: String?,
    val District: String?,
    val Division: String?,
    val Name: String?,
    val Region: String?,
    val State: String?
)