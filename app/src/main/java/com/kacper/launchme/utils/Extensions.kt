package com.kacper.launchme.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.kacper.launchme.R

fun NavController.navigateWithTransition(navigationID: Int) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.out_to_left)
        .setExitAnim(R.anim.in_from_right)
        .setPopEnterAnim(R.anim.in_from_left)
        .setPopExitAnim(R.anim.out_to_right)
        .build()

    navigate(navigationID, null, navOptions)
}

fun Fragment.requireActivity() = activity!!