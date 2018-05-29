package com.casas.fabiel.zemogaapp.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.zemogaapp.R
import com.casas.fabiel.zemogaapp.utils.StringsUtils
import kotlinx.android.synthetic.main.item_post.view.*

class PostItem : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_post, this, true)
    }

    fun bind(posts: Posts) {
        textViewPostTitle.text = StringsUtils.capitalizeFirstLetter(posts.title)
    }

}