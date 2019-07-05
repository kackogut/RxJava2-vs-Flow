package com.kacper.launchme.data.launch

import com.google.gson.annotations.SerializedName

class LaunchLinks(
    @SerializedName("mission_patch")
    val missionPatch: String,

    @SerializedName("mission_patch_small")
    val missionPatchSmall: String
)