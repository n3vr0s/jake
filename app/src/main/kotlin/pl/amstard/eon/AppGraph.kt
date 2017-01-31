package pl.amstard.eon

import pl.amstard.eon.views.RepoListItemView

interface AppGraph {
    fun inject(application: App)
    fun inject(repoListItemView: RepoListItemView)
}