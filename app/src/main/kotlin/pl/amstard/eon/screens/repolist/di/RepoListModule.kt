package pl.amstard.eon.screens.repolist.di

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import pl.amstard.eon.annotation.ApplicationContext
import pl.amstard.eon.screens.repolist.RepoAdapter
import pl.amstard.eon.screens.repolist.RepoListActivity
import pl.amstard.eon.screens.repolist.RepoListPresenter
import pl.amstard.eon.usecases.RepoUseCase

@Module
class RepoListModule(val repoListActivity: RepoListActivity) {

    @Provides
    @RepoListScope
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.isItemPrefetchEnabled = true
        return linearLayoutManager
    }

    @Provides
    @RepoListScope
    fun provideRepoAdapter(): RepoAdapter {
        return RepoAdapter()
    }

    @Provides
    @RepoListScope
    fun providePresenter(repoUseCase: RepoUseCase) : RepoListPresenter {
        return RepoListPresenter(repoUseCase)
    }
}