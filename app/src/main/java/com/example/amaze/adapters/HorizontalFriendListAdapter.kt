/*
 * Developed by Yann Malanda on 5/25/19 4:24 PM.
 * Last modified 5/19/19 12:59 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import com.example.amaze.models.Guest
import com.example.amaze.network.SearchedGuest
import com.example.amaze.network.UserResult
import kotlinx.android.synthetic.main.component_event_card.view.*
import kotlinx.android.synthetic.main.custom_horizontal_friend_list_item.view.*

class HorizontalFriendListAdapter(val guests: ArrayList<SearchedGuest>): RecyclerView.Adapter<HorizontalFriendListAdapter.HorizontalFriendListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HorizontalFriendListViewHolder {
        return HorizontalFriendListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_horizontal_friend_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return guests.count()
    }

    override fun onBindViewHolder(holder: HorizontalFriendListViewHolder, position: Int) {
        val guest = guests[position]

        holder.view.custom_horizontal_friend_list_item_name.text = guest.firstName
    }


    class HorizontalFriendListViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        override fun onClick(v: View?) {
        }

    }
}