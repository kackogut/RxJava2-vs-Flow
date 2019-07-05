package com.kacper.launchme.data.launch

import com.google.gson.annotations.SerializedName

class LaunchSite(
    @SerializedName("site_name")
    val name: String,

    @SerializedName("site_name_long")
    val nameLong: String
)