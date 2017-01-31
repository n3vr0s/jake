package pl.amstard.eon.screens.repolist

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.amstard.eon.R
import pl.amstard.eon.communications.responses.RepoResponse
import pl.amstard.eon.views.RepoListItemView

class RepoItemViewHolder : ViewHolder {

    val postListItemView: RepoListItemView

    constructor(viewGroup: ViewGroup) : super(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_item_post_item, viewGroup, false)){
        postListItemView = itemView as RepoListItemView
    }

    fun bindItem(repoResponse: RepoResponse){
        postListItemView.bindItem(repoResponse)
    }
}