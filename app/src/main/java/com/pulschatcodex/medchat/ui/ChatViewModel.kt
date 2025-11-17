package com.pulschatcodex.medchat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pulschatcodex.medchat.data.BotClient
import com.pulschatcodex.medchat.data.ChatMessage
import com.pulschatcodex.medchat.data.InMemoryProtocolRepository
import com.pulschatcodex.medchat.data.MessageSender
import kotlinx.coroutines.launch
import java.util.UUID

class ChatViewModel(
    private val botClient: BotClient = BotClient(InMemoryProtocolRepository())
) : ViewModel() {

    private val _messages = androidx.compose.runtime.mutableStateListOf<ChatMessage>()
    val messages: List<ChatMessage> get() = _messages

    init {
        _messages.add(
            ChatMessage(
                id = UUID.randomUUID().toString(),
                sender = MessageSender.BOT,
                content = "How can I help with your clinical workflow today?"
            )
        )
    }

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        val trimmed = text.trim()
        _messages.add(
            ChatMessage(
                id = UUID.randomUUID().toString(),
                sender = MessageSender.CLINICIAN,
                content = trimmed
            )
        )

        viewModelScope.launch {
            val botReply = botClient.ask(trimmed)
            _messages.add(botReply)
        }
    }
}
