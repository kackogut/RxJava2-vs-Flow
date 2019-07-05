package com.kacper.launchme.di.component

import android.app.Application
import com.kacper.LaunchMeApp
import com.kacper.launchme.di.module.ActivityModule
import com.kacper.launchme.di.module.AppModule
import com.kacper.launchme.di.module.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        DataModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(launchMeApp: LaunchMeApp)
}
