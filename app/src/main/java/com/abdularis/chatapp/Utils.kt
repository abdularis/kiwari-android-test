package com.abdularis.chatapp

import java.text.SimpleDateFormat
import java.util.*

fun formatTimestamp(timestamp: Long): String {
    val format = SimpleDateFormat("MM-dd yyyy, HH:mm", Locale.getDefault())
    return format.format(Date(timestamp))
}