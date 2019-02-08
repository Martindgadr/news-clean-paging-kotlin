package com.ionnt.newscleanarchkotlin.commons.exception

/**
 * Created by Martin De Girolamo on 23/01/2019.
 * Base Class for handling errors/failures/exceptions.
 */

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    /**  Extend this class for specific failures. */
    abstract class FeatureFailure: Failure()
}
