package pl.amstard.eon.screens.repolist

import me.n3vr0s.auberginekotlin.MvpView
import pl.amstard.eon.communications.responses.RepoResponse

interface RepoListView : MvpView {
    fun displayRepos(it: List<RepoResponse>)
}