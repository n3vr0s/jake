package pl.amstard.eon.screens.dashboard.di

import dagger.Subcomponent
import pl.amstard.eon.screens.repolist.RepoListActivity
import pl.amstard.eon.screens.repolist.RepoListLayout
import pl.amstard.eon.screens.repolist.di.RepoListModule
import pl.amstard.eon.screens.repolist.di.RepoListScope

@Subcomponent(
        modules = arrayOf(RepoListModule::class)
)
@RepoListScope
interface RepoListComponent {
    fun inject(repoListLayout: RepoListLayout)
    fun inject(repoListActivity: RepoListActivity)
}