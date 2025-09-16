package com.example.myassssmentapplication

import com.google.gson.annotations.SerializedName

data class DashboardResponse(
    val entities: List<EntityDto>,
    val entityTotal: Int
)

data class EntityDto(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("director")
    val director: String? = null,
    @SerializedName("genre")
    val genre: String? = null,
    @SerializedName("releaseYear")
    val releaseYear: Int? = null,
    @SerializedName("description")
    val description: String? = null
)
