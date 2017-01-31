package pl.amstard.eon.screens.repolist

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import butterknife.bindView
import me.n3vr0s.auberginekotlin.AubergineCoordinatorLayout
import pl.amstard.eon.R
import pl.amstard.eon.communications.responses.RepoResponse
import pl.amstard.eon.screens.repolist.di.RepoListModule
import pl.amstard.eon.screens.repolist.di.RepoListScope
import javax.inject.Inject

class RepoListLayout(context: Context?, attrs: AttributeSet?) : AubergineCoordinatorLayout<RepoListPresenter>(
        context,
        attrs) {

    val rvPosts by bindView<RecyclerView>(R.id.rvRepos)

    @Inject @RepoListScope lateinit var linearLayoutManager: LinearLayoutManager
    @Inject @field:RepoListScope lateinit var repoAdapter: RepoAdapter

    override fun injectDependencies() {
        (context as? RepoListActivity)?.let {
            repoListActivity -> repoListActivity.component?.plus(RepoListModule(repoListActivity))?.inject(this)
        }

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        rvPosts.layoutManager = linearLayoutManager
        rvPosts.adapter = repoAdapter
    }

    fun displayRepoList(repos: List<RepoResponse>) {
        repoAdapter.addRepos(repos)
        repoAdapter.notifyDataSetChanged()
    }
}
