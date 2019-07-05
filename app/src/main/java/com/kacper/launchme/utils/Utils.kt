package com.kacper.launchme.utils

import androidx.paging.PagedList

class Utils {
    companion object {
        fun getDefaultPagedListConfig() = PagedList.Config.Builder()
            .setPageSize(Constants.LIST_BASE_LIMIT)
            .setInitialLoadSizeHint(Constants.LIST_BASE_LIMIT)
            .setEnablePlaceholders(false)
            .build()
    }
}