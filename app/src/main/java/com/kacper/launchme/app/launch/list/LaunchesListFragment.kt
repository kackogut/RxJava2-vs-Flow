package com.kacper.launchme.app.launch.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kacper.launchme.app.launch.LaunchViewModel
import com.kacper.launchme.databinding.FragmentLaunchListBinding
import com.kacper.launchme.di.Injectable
import javax.inject.Inject

class LaunchesListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val launchViewModel: LaunchViewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(LaunchViewModel::class.java)
    }

    private lateinit var launchesAdapter: LaunchesAdapter

    private val onDataSourceChangeCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            initList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val launchListBinding =
            FragmentLaunchListBinding.inflate(inflater, container, false)

        launchListBinding.viewModel = launchViewModel

        initAdapter(launchListBinding)
        initList()
        initViewModelListener()

        return launchListBinding.root
    }

    private fun initList() {
        launchViewModel.initLaunchesList()

        launchViewModel.launchesList?.observe(this, Observer {
            launchesAdapter.submitList(it)
        })
    }

    private fun initAdapter(launchListBinding: FragmentLaunchListBinding) {
        launchesAdapter = LaunchesAdapter { launch ->
            launchViewModel.getLaunchDetails(
                launch.flightNumber
            )
        }

        launchListBinding.rvBaseLayout.adapter = launchesAdapter
    }

    private fun initViewModelListener() {
        launchViewModel.isFlowEnabled.addOnPropertyChangedCallback(onDataSourceChangeCallback)
    }

    override fun onDestroyView() {
        launchViewModel.isFlowEnabled.removeOnPropertyChangedCallback(onDataSourceChangeCallback)
        super.onDestroyView()
    }
}