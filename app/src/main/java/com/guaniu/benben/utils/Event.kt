package com.guaniu.benben.utils

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun handle(handler: (T) -> Unit) {
        if (!hasBeenHandled) {
            hasBeenHandled = true
            handler(content)
        }
    }
}