package com.example.myapplication.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("image_url") val imageUrl: String?,  // Nullable type
    @SerialName("author") val author: String,
    @SerialName("published_date") val publishedDate: String?,  // Nullable type
    @SerialName("views") val views: Int,
    @SerialName("is_published") val isPublished: Boolean
)
