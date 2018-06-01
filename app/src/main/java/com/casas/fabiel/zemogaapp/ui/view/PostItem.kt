package com.casas.fabiel.zemogaapp.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.zemogaapp.R
import com.casas.fabiel.zemogaapp.utils.StringsUtils
import kotlinx.android.synthetic.main.item_post.view.*

class PostItem : LinearLayout {

    private lateinit var listener: (posts: Posts) -> Unit

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_post, this, true)
    }

    fun bind(posts: Posts) {
        textViewPostTitle.text = StringsUtils.capitalizeFirstLetter(posts.title)
        imageViewFavorite.visibility = shouldShowView(posts.isFavorite)
        imageViewRead.visibility = shouldShowView(posts.viewCount == 0)
        setOnClickListener({
            listener(posts)
        })
    }

    fun shouldShowView(validation: Boolean): Int {
        return if (validation) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }


    fun setItemListener(listener: (posts: Posts) -> Unit) {
        this.listener = listener
    }

}