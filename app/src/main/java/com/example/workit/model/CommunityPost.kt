package com.example.workit.model

data class CommunityPost(
    val userName: String,
    val timePosted: String,
    val postContent: String,
    val profilePicture: Int = 0
)