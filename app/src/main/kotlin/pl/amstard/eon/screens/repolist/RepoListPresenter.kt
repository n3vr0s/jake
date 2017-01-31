package pl.amstard.eon.screens.repolist

import pl.amstard.eon.base.RxAuberginePresenterImpl
import pl.amstard.eon.screens.repolist.RepoListView
import pl.amstard.eon.usecases.RepoUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoListPresenter
@Inject constructor(val repoUseCase: RepoUseCase)
    : RxAuberginePresenterImpl<RepoListView, RepoListViewModel>() {


    override fun prepareViewModel(): RepoListViewModel {
        return RepoListViewModel()
    }

    override fun bindView(view: RepoListView) {
        super.bindView(view)
        addSubscriberToSubscription(
                repoUseCase.getJakeRepos(1)
        ,
                successAction = {
                    view.displayRepos(it)
                }

        )
    }
}
