package com.ememos.app.data

data class SignInRequest(
    val email: String,
    val password: String
)

data class SignInResponse(
    val accessToken: String
)