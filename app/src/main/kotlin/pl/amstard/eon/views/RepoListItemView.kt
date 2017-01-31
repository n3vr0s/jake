package pl.amstard.eon.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.bindView
import com.squareup.picasso.Picasso
import pl.amstard.eon.App
import pl.amstard.eon.R
import pl.amstard.eon.communications.responses.RepoResponse
import javax.inject.Inject

class RepoListItemView : RelativeLayout {

    val ivAvatar by bindView<ImageView>(R.id.ivAvatar)
    val tvName by bindView<TextView>(R.id.tvName)
    val tvDescription by bindView<TextView>(R.id.tvDescription)
    val tvStarCount by bindView<TextView>(R.id.tvStarCount)

    @Inject lateinit var picasso: Picasso

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if(!isInEditMode){
            App.appComponent.inject(this)
        }
    }

    fun bindItem(post: RepoResponse) {
        tvName.text = post.name
        tvDescription.text = post.description
        tvDescription.text = post.description
        tvStarCount.text = post.starCount.toString()
    }

}