package com.casas.fabiel.zemogaapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.casas.fabiel.data.repository.entities.Posts
import com.casas.fabiel.zemogaapp.ui.view.PostItem

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var postsLists: List<Posts> = mutableListOf()
    private lateinit var listener: (posts: Posts) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var item = PostItem(parent.context)
        item.setItemListener(listener)
        return PostViewHolder(item)
    }

    override fun getItemCount(): Int {
        return postsLists.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postsLists[position])
    }


    fun setPostItemListener(listener: (posts:Posts) -> Unit) {
        this.listener = listener
    }

    class PostViewHolder(var item: PostItem?) : RecyclerView.ViewHolder(item) {

        fun bind(post: Posts) {
            item!!.bind(post)
        }

    }
}