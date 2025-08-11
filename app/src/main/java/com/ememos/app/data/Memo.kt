package com.ememos.app.data

data class Memo(
    val id: String,
    val content: String,
    val visibility: String,
    val createdTs: Long,
    val updatedTs: Long,
    val creatorId: Int
)