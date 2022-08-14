package com.westig.frame.utils

class Resource<T>(var data: T?) {
    var code: String = "success"
    var success: Boolean = false
        get() = code == "success"
    var message: String = ""

    companion object {
        fun <T> with(data: T): Resource<T> {
            val res = Resource(data)
            return res
        }

        fun <T> fail(code: String, message: String): Resource<T> {
            val res = Resource<T>(null)
            res.success = false
            res.code = code
            res.message = message
            return res
        }
    }
}