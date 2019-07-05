package com.kacper.launchme.app.launch.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kacper.launchme.R
import com.kacper.launchme.app.launch.LaunchViewModel
import com.kacper.launchme.databinding.FragmentLaunchListBinding
import com.kacper.launchme.di.Injectable
import com.kacper.launchme.utils.navigateWithTransition
import javax.inject.Inject

class LaunchesListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val launchViewModel: LaunchViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)
            .get(LaunchViewModel::class.java)
    }

    private lateinit var launchesAdapter: LaunchesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val launchListBinding =
            FragmentLaunchListBinding.inflate(inflater, container, false)

        launchListBinding.viewModel = launchViewModel
        initList(launchListBinding)

        return launchListBinding.root
    }

    private fun initList(launchListBinding: FragmentLaunchListBinding) {

        launchViewModel.initLaunchesList()

        launchesAdapter = LaunchesAdapter { launch ->
            launchViewModel.currentLaunch = launch
            findNavController().navigateWithTransition(
                R.id.action_launchListFragment_to_launchDetailsFragment
            )
        }

        launchListBinding.rvBaseLayout.adapter = launchesAdapter

        launchViewModel.launchesList?.observe(this, Observer {
            launchesAdapter.submitList(it)
        })
    }
}