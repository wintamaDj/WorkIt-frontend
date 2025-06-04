package com.example.workit.model

import java.util.Date

data class User (
    var id: Int,
    var name: String,
    var email : String,
    var password: String
//    var memberType: String, // Free, Premium, Pro
//    var joinDate : Date? = null
)