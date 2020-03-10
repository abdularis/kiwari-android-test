package com.abdularis.chatapp

data class Message (
    val text: String,
    val timestamp: Long,
    val senderEmail: String,
    val isSent: Boolean
)

data class MessageData(
    val text: String = "",
    val timestamp: Long = 0,
    val sender: String = ""
)
