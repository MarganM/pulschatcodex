package com.pulschatcodex.medchat.data

enum class MessageSender {
    CLINICIAN,
    BOT
}

data class ChatMessage(
    val id: String,
    val sender: MessageSender,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)
