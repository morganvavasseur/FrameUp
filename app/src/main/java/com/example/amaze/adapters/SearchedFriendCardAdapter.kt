package com.example.amaze.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import com.example.amaze.models.User
import com.example.amaze.network.UserResult
import kotlinx.android.synthetic.main.component_searched_friend_card.view.*
import java.io.File
import java.lang.Number

class SearchedFriendCardAdapter(val users: List<UserResult>, val onFriendItemListener:OnFriendItemListener) : RecyclerView.Adapter<SearchedFriendCardAdapter.SearchedFriendCardViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SearchedFriendCardViewHolder {
        return SearchedFriendCardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.component_searched_friend_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    override fun onBindViewHolder(holder: SearchedFriendCardViewHolder, position: Int){
        val user = users[position]

        holder.view.setOnClickListener( {onFriendItemListener.onFriendClick(user) })
        holder.view.searchFriendName.text = user.firstName + " " + user.lastName
        holder.view.searchedFriendUsername.text = "@"+user.username
    }


    class SearchedFriendCardViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        override fun onClick(v: View?) {

        }

    }

    interface OnFriendItemListener {
        fun onFriendClick(position: UserResult)
    }
}

