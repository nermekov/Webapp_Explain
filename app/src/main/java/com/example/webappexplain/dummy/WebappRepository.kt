package com.example.webappexplain.dummy

internal interface WebappRepository {
    fun getWebappDebugHost(webappId: Int): String?

    fun setWebappDebugHost(host: String?, webappId: Int)
}