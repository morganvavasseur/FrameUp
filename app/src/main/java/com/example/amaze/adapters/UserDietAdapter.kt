package com.example.amaze.adapters

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import com.example.amaze.models.Diet

class UserDietAdapter (private val diets : ArrayList<Diet>) : Adapter<UserDietAdapter.UserDietViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDietViewHolder {
        return UserDietViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_info, parent, false))
    }

    override fun getItemCount() = diets.size

    override fun onBindViewHolder(holder: UserDietAdapter.UserDietViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class UserDietViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}