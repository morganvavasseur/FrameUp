/*
 * Developed by Yann Malanda on 6/4/19 11:39 AM.
 * Last modified 6/4/19 11:39 AM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import com.example.amaze.models.Guest
import kotlinx.android.synthetic.main.invited_guest_adapter.view.*

class InvitedGuestAdapter(val guestList : List<Guest>) : RecyclerView.Adapter<InvitedGuestAdapter.InvitedGuestCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): InvitedGuestCardViewHolder {
        return InvitedGuestCardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.invited_guest_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    override fun onBindViewHolder(holder: InvitedGuestCardViewHolder, position: Int) {
        try {
            val guest = guestList[position]

            if (guest != null){
                if (guest.firstName != null && guest.lastName != null)
                    holder.view.invitedGuestName.text = guest.firstName + " " + guest.lastName
                if (guest.username != null)
                    holder.view.invitedGuestUsername.text = "@"+guest.username
            }

        } catch (e: Error) {
            Log.d("Error", e.message)
        }
    }

    class InvitedGuestCardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }
}