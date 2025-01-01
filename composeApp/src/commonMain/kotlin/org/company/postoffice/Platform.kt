package org.company.postoffice

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform