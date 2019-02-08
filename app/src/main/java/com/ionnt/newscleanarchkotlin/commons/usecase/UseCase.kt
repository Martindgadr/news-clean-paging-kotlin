package com.ionnt.newscleanarchkotlin.commons.usecase

import com.ionnt.newscleanarchkotlin.commons.exception.Failure
import com.ionnt.newscleanarchkotlin.commons.utils.Either
import kotlinx.coroutines.*

/**
 * Created by Martin De Girolamo on 25/01/2019.
 * Use case must be implemented as firm and use kotlin coroutines as background thread
 */
abstract class UseCase<out Type, in Params> where Type : Any {
    private val mainJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + mainJob)

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) =
        uiScope.launch { onResult(withContext(Dispatchers.IO) { run(params) }) }

    fun cancel() = mainJob.cancel()

    class None
}