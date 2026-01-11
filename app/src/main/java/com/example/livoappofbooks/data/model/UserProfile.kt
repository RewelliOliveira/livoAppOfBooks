package com.example.livoappofbooks.data.model

import com.squareup.moshi.Json

data class UserProfile(
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "reading") val reading: Int,
    @Json(name = "read") val read: Int
)