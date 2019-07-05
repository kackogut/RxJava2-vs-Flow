package com.kacper.launchme.data.list

import com.kacper.launchme.utils.Constants

class BaseListRequest(
    val limit: Int = Constants.LIST_BASE_LIMIT,

    var offset: Int = 0
) {
    fun toMap() =
        mapOf("limit" to limit, "offset" to offset)
}