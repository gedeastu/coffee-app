package com.example.coffeeapp.persentation.sign_in

data class SignInResult(
    val data: com.example.coffeeapp.persentation.sign_in.UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId:String,
    val userName: String?,
    val profilePictureUrl: String?
)