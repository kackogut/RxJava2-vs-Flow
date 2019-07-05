package com.kacper.launchme.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.kacper.launchme.R
import com.kacper.launchme.data.BaseState
import com.kacper.launchme.data.launch.Launch

@BindingAdapter("image")
fun bindImage(imageView: ImageView, imageURL: String?) {
    GlideUtils.loadImage(imageView, imageURL)
}

@BindingAdapter("status_text")
fun bindStatusText(textView: TextView, status: Int) {
    textView.setText(
        when (status) {
            Launch.STATUS_UPCOMING -> R.string.upcoming
            Launch.STATUS_FAILURE -> R.string.failure
            else -> R.string.success
        }
    )
}

@BindingAdapter("status_color")
fun bindStatusColor(textView: TextView, status: Int) {
    textView.setTextColor(
        ContextCompat.getColor(
            textView.context,
            when (status) {
                Launch.STATUS_UPCOMING -> R.color.colorStatusUpcoming
                Launch.STATUS_FAILURE -> R.color.colorStatusFailure
                else -> R.color.colorStatusSuccess
            }
        )
    )
}

@BindingAdapter("visibility")
fun bindVisibility(view: View, visibility: Boolean) {
    view.visibility = if (visibility) View.VISIBLE else View.GONE
}

@BindingAdapter("error_resource")
fun bindErrorResource(view: View, state: BaseState) {
    view.visibility = if (state is BaseState.OnError) View.VISIBLE else View.GONE
}

@BindingAdapter("success_resource")
fun bindSuccessResource(view: View, state: BaseState) {
    view.visibility = if (state is BaseState.Success) View.VISIBLE else View.GONE
}

@BindingAdapter("loading_resource")
fun bindLoadingResource(view: View, state: BaseState) {
    view.visibility = if (state is BaseState.Loading) View.VISIBLE else View.GONE
}