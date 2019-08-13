package com.kacper.launchme.app.launch.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kacper.launchme.app.launch.LaunchViewModel
import com.kacper.launchme.databinding.FragmentLaunchDetailsBinding
import com.kacper.launchme.di.Injectable
import javax.inject.Inject

class LaunchDetailsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val launchViewModel: LaunchViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(LaunchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val launchDetailsBinding = FragmentLaunchDetailsBinding.inflate(inflater, container, false)

        launchDetailsBinding.launchViewModel = launchViewModel

        return launchDetailsBinding.root
    }
}