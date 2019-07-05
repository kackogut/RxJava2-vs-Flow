package com.kacper.launchme.data.launch

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Launch(

    @SerializedName("flight_number")
    val flightNumber: Int,

    @SerializedName("mission_name")
    val missionName: String,

    @SerializedName("rocket")
    val rocket: Rocket,

    @SerializedName("links")
    val links: LaunchLinks,

    @SerializedName("launch_site")
    val launchSite: LaunchSite,

    @SerializedName("details")
    val details: String,

    @SerializedName("launch_success")
    val launchSuccess: Boolean?,

    @SerializedName("upcoming")
    val isUpcoming: Boolean?
) {
    fun getStatus() =
        launchSuccess?.let {
            if (it) STATUS_SUCCESS
            else STATUS_FAILURE
        } ?: STATUS_UPCOMING


    companion object {
        fun getDiffCallback() =
            object : DiffUtil.ItemCallback<Launch>() {
                override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
                    return oldItem.flightNumber == newItem.flightNumber
                }

                override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
                    return oldItem == newItem
                }
            }

        const val STATUS_SUCCESS = 1
        const val STATUS_FAILURE = 2
        const val STATUS_UPCOMING = 3

    }
}
