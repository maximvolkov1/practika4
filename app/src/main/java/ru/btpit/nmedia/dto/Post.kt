package ru.btpit.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 1300000,
    var likedByMe: Boolean = false,
    var shares: Int = 1300000,
    var sharedByMe: Boolean = false,
    var viewes: Int = 1300000 + 1,
    var viewedByMe: Boolean = false
)

