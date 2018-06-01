package com.casas.fabiel.zemogaapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.casas.fabiel.data.repository.entities.Comments
import com.casas.fabiel.zemogaapp.ui.view.CommentItem

class CommentsAdapter: RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    var commentsList: List<Comments> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var view = CommentItem(parent.context)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentsList[position])
    }

    class CommentViewHolder(var view: CommentItem?) : RecyclerView.ViewHolder(view) {

        fun bind(comment: Comments) {
            view!!.bind(comment)
        }
    }

}