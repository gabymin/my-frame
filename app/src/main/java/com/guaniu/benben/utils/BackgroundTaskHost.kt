package com.guaniu.benben.utils

interface BackgroundTaskHost <T>{

    fun backgroundTaskStart()
    fun backgroundTaskStop(result:T?,throwable: Throwable?)
}