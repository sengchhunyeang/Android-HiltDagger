package com.example.myapplication.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("message") val message: String,
    @SerialName("payload") val payload: List<Article>,
    @SerialName("status") val status: String,
    @SerialName("timestamp") val timestamp: String
)
