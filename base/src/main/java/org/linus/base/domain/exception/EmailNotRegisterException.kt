package org.linus.base.domain.exception

import java.io.Serializable

sealed class AppException: Throwable(), Serializable {
    object NetworkNotConnectedException : AppException()
    object EmailNotRegisteredException : AppException()
    object UnknownException : AppException()
    class ServerException(message: String?): AppException()
}