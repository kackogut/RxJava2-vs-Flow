package com.kacper.launchme.app.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.kacper.launchme.R
import com.kacper.launchme.app.launch.list.LaunchState
import com.kacper.launchme.databinding.ActivityLaunchBinding
import com.kacper.launchme.utils.navigateWithTransition
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

        launchViewModel.state.addOnPropertyChangedCallback(
            object : Observable.OnPropertyChangedCallback(){
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if(launchViewModel.state.get() == LaunchState.OnLaunchDetailsFetched){
                        findNavController(this@LaunchActivity, R.id.nav_launch_fragment).navigateWithTransition(
                            R.id.action_launchListFragment_to_launchDetailsFragment
                        )
                    }
                }

            }
        )
    }

}
