package pl.amstard.eon.screens.repolist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import pl.amstard.eon.communications.responses.RepoResponse
import pl.amstard.eon.screens.repolist.RepoItemViewHolder

class RepoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var repos: MutableList<RepoResponse> = mutableListOf()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepoItemViewHolder).bindItem(repos[position])
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return RepoItemViewHolder(parent)
    }

    fun addRepos(items: List<RepoResponse>) {
        repos.addAll(items)
    }
}