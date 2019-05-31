/*
 * Developed by Yann Malanda on 5/30/19 10:00 PM.
 * Last modified 5/30/19 10:00 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.adapters

import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.amaze.AmazeApp
import com.example.amaze.R
import com.example.amaze.models.Place
import com.example.amaze.network.SearchedGuest
import kotlinx.android.synthetic.main.component_searched_friend_card.view.*
import kotlinx.android.synthetic.main.founded_places_item.view.*

class FoundedPlacesItemAdapter(val foundedPlaces : List<Place>, val foundedPlaceItemListener: OnFoundedPlaceItemListener) : RecyclerView.Adapter<FoundedPlacesItemAdapter.FoundedPlacesItemViewHolder>() {

    lateinit var selectedPlace : Place

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FoundedPlacesItemViewHolder {
        return FoundedPlacesItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.founded_places_item, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return foundedPlaces.count()
    }

    override fun onBindViewHolder(holder: FoundedPlacesItemViewHolder, position: Int) {

        try {
            val foundedPlace = foundedPlaces[position]

            if (foundedPlace != null) {
                holder.view.placeName.text = foundedPlace.name
                holder.view.placeAddress.text = foundedPlace.formattedAddress
            }

            holder.view.setOnClickListener {foundedPlaceItemListener.onFoundedPlaceClick(foundedPlace)}

        } catch (e: Error) {
            Log.d("Error", e.message)
        }

    }

    class FoundedPlacesItemViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    interface OnFoundedPlaceItemListener {
        fun onFoundedPlaceClick(clickedPlace: Place)
    }

}