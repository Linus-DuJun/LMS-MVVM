package org.linus.base.util.network

import org.linus.base.domain.exception.AppException

suspend fun <T> requestApiWithNetworkChecking(
    networkManager: NetworkManager,
    apiCall: suspend () -> T
): T {
    return if (networkManager.isConnected) {
        apiCall.invoke()
    } else {
        error(AppException.NetworkNotConnectedException)
    }
}