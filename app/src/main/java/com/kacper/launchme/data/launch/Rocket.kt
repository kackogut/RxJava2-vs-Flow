package com.kacper.launchme.data.launch

import com.google.gson.annotations.SerializedName

class Rocket(
    @SerializedName("rocket_name")
    val name: String,

    @SerializedName("rocket_type")
    val type: String
)