package com.example.workit.model

data class Review(
    val reviewerName: String,
    val jobTitle: String,
    val location: String,
    val rating: Float,
    val date: String,
    val reviewText: String,
    val benefitsRating: Float = 5.0f,
    val workLifeRating: Float = 5.0f,
    val environmentRating: Float = 5.0f,
    val careerRating: Float = 5.0f,
    val profileImageResId: Int = 0
)
