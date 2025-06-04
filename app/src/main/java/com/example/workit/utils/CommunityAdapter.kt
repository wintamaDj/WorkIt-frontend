package com.example.workit.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workit.model.CommunityPost
import com.example.workit.R

class CommunityAdapter(private var posts: MutableList<CommunityPost>) :
    RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder>() {

    class CommunityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePic: ImageView = itemView.findViewById(R.id.iv_profile_pic)
        val userName: TextView = itemView.findViewById(R.id.tv_user_name)
        val timePosted: TextView = itemView.findViewById(R.id.tv_time_posted)
        val postContent: TextView = itemView.findViewById(R.id.tv_post_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.community_forum_list, parent, false)
        return CommunityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        val post = posts[position]

        holder.userName.text = post.userName
        holder.timePosted.text = post.timePosted
        holder.postContent.text = post.postContent

        if (post.profilePicture != 0) {
            holder.profilePic.setImageResource(post.profilePicture)
        } else {
            holder.profilePic.setImageResource(R.drawable.round_shape)
        }
    }

    override fun getItemCount(): Int = posts.size

    fun updateData(newPosts: List<CommunityPost>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }
}