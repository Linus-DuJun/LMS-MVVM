package org.linus.base.domain.usecase

import kotlinx.coroutines.*
/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class BaseUseCase<out Type, in Params> where Type: Any? {

    abstract suspend fun run(params: Params): Type

    suspend operator fun invoke(
        params: Params,
        onResult: (Type) -> Unit = {}
    ) {
        try {
            val result = run(params)
           withContext(Dispatchers.Main) {
               onResult(result) // callback on main thread
           }
        } catch (e: Exception) {
            throw e // delegate error to CoroutineExceptionHandler.
        }
    }

    class None
}