package com.kacper.launchme.app.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.kacper.launchme.R
import com.kacper.launchme.app.launch.details.LaunchDetailsFragment
import com.kacper.launchme.app.launch.list.LaunchesListFragment
import com.kacper.launchme.databinding.ActivityLaunchBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class LaunchActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private lateinit var binding: ActivityLaunchBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val launchViewModel: LaunchViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(LaunchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_launch)
        binding.viewModel = launchViewModel
    }

}
