package org.linus.lms.auth.data.network.entry

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResultEntry(
    val userId: String,
    val isNewUser: Boolean
)