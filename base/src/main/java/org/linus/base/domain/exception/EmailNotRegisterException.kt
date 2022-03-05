package org.linus.base.domain.exception

import java.io.Serializable

sealed class AppException(
   message: String?
): Throwable(message = message), Serializable {
    object NetworkNotConnectedException : AppException(message = "Network not connected")
    object UnknownException : AppException(message = "Unknown error")
    class EmailNotRegisteredException(email: String) : AppException(message = null)
    class ServerException(message: String?): AppException(message = message)
}