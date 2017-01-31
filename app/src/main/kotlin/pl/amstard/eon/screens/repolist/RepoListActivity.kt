package pl.amstard.eon.screens.repolist

import android.view.View
import butterknife.bindView
import me.n3vr0s.auberginekotlin.AubergineActivity
import pl.amstard.eon.App
import pl.amstard.eon.AppComponent
import pl.amstard.eon.R
import pl.amstard.eon.communications.responses.RepoResponse
import pl.amstard.eon.screens.repolist.di.RepoListModule
import javax.inject.Inject



class RepoListActivity : AubergineActivity<RepoListPresenter, RepoListView, AppComponent>(), RepoListView {

    override fun createDaggerComponent(): AppComponent {
        return App.appComponent
    }

    @Inject lateinit override var presenter: RepoListPresenter

    val repoListLayout by bindView<RepoListLayout>(R.id.repoListLayout)

    override fun getMvpView(): RepoListView {
        return this
    }

    override fun createView(): View {
        return layoutInflater.inflate(R.layout.activity_repo_list, null, false)
    }

    override fun injectDependencies() {
        component?.plus(RepoListModule(this))?.inject(this)
    }

    override fun displayRepos(it: List<RepoResponse>) {
        repoListLayout.displayRepoList(it)
    }

}



