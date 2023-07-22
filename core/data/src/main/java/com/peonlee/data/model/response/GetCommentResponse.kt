package com.peonlee.data.model.response

import com.peonlee.data.model.Comment
import kotlinx.serialization.Serializable

@Serializable
data class GetCommentResponse(
    val content: List<Comment>,
    val lastId: Int?
)
