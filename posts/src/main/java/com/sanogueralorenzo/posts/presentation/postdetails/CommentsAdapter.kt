package com.sanogueralorenzo.posts.presentation.postdetails

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sanogueralorenzo.posts.R
import com.sanogueralorenzo.posts.presentation.loadAvatar
import com.sanogueralorenzo.posts.presentation.model.CommentItem
import com.sanogueralorenzo.presentation.inflate
import kotlinx.android.synthetic.main.include_user_info_small.view.*
import kotlinx.android.synthetic.main.item_list_comment.view.*
import java.util.ArrayList

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    private val items = ArrayList<CommentItem>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_list_comment)) {

        fun bind(item: CommentItem) {
            itemView.userAvatar.loadAvatar(item.email)
            itemView.userName.text = item.name.capitalize()
            itemView.commentBody.text = item.body.capitalize()
        }
    }

    fun addItems(list: List<CommentItem>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }
}
