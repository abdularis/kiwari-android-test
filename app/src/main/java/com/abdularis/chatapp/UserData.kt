package com.abdularis.chatapp

data class UserData(
    val name: String,
    val avatar: String,
    val email: String,
    val password: String
)

val USER_JARJIT = UserData(
    name = "Jarjit Singh",
    avatar = "https://api.adorable.io/avatars/160/jarjit@mail.com.png",
    email = "jarjit@mail.com",
    password = "123456"
)

val USER_MAIL = UserData(
    name = "Ismail bin Mail",
    avatar = "https://api.adorable.io/avatars/160/ismail@mail.com.png",
    email = "ismail@mail.com",
    password = "123456"
)

val USER_DATA_LIST = arrayOf(
    USER_JARJIT,
    USER_MAIL
)