package com.pulschatcodex.medchat.data

import java.util.UUID
import kotlinx.coroutines.delay

interface ProtocolRepository {
    suspend fun fetchProtocolSummary(question: String): String
}

class InMemoryProtocolRepository : ProtocolRepository {
    private val protocols = mapOf(
        "sepsis" to "Use qSOFA or NEWS for screening, obtain lactate, draw cultures, start broad-spectrum antibiotics within 1 hour, and begin fluid resuscitation.",
        "stroke" to "Follow BE-FAST, obtain non-contrast CT within 20 minutes of arrival, and assess tPA eligibility including blood pressure and glucose checks.",
        "chest pain" to "Use HEART score, obtain ECG and troponin, provide aspirin unless contraindicated, and rule out STEMI before discharge.",
        "pediatrics" to "Use weight-based dosing, prefer oral rehydration first, and reassess frequently for age-specific vital sign norms."
    )

    override suspend fun fetchProtocolSummary(question: String): String {
        delay(350) // simulate network or compute latency
        val key = protocols.keys.firstOrNull { question.contains(it, ignoreCase = true) }
        val summary = key?.let { protocols[it] }
        return summary ?: "I could not find a direct match. Please specify the condition, such as sepsis, stroke, or chest pain."
    }
}

class BotClient(private val protocolRepository: ProtocolRepository) {
    suspend fun ask(question: String): ChatMessage {
        val summary = protocolRepository.fetchProtocolSummary(question)
        return ChatMessage(
            id = UUID.randomUUID().toString(),
            sender = MessageSender.BOT,
            content = "Protocol insight: $summary"
        )
    }
}
