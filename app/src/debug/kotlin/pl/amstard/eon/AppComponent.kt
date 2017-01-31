package pl.amstard.eon

import dagger.Component
import pl.amstard.eon.api.CoreApiModule
import pl.amstard.eon.api.DebugApiModule
import pl.amstard.eon.api.UseCaseModule
import pl.amstard.eon.screens.dashboard.di.RepoListComponent
import pl.amstard.eon.screens.repolist.di.RepoListModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class,
                             CoreApiModule::class,
                             UseCaseModule::class,
                             DebugApiModule::class))
interface AppComponent : AppGraph, AppDependencies {
    fun plus(repoListModule: RepoListModule): RepoListComponent
}