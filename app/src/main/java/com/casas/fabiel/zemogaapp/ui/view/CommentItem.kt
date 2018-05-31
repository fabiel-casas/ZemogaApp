package com.casas.fabiel.zemogaapp.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.casas.fabiel.data.repository.entities.Comments
import com.casas.fabiel.zemogaapp.R
import com.casas.fabiel.zemogaapp.utils.StringsUtils
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentItem: LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_comment, this, true)
    }

    fun bind(comments: Comments) {
        textViewUserName.text = StringsUtils.capitalizeText(comments.name)
        textViewCommentResume.text = StringsUtils.capitalizeFirstLetter(comments.body)
    }
}