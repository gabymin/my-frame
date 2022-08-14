package com.westig.frame.utils

interface BackgroundTaskHost <T>{

    fun backgroundTaskStart()
    fun backgroundTaskStop(result:T?,throwable: Throwable?)
}