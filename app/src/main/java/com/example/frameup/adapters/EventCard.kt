package com.example.frameup.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.example.amaze.R

class EventCard(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    

    init {
        inflate(context, R.layout.component_event_card, this)

        // Initilise les éléments
        val profileImageView: ImageView = findViewById(R.id.profile_image)
        val eventTitle: TextView = findViewById(R.id.event_card_title)
        val eventDate: TextView = findViewById(R.id.event_card_date)
        val eventHour: TextView = findViewById(R.id.event_card_hour)
        val eventDescription: TextView = findViewById(R.id.event_card_description)
        val eventLocation: TextView = findViewById(R.id.event_card_location)
        val eventHostName: TextView = findViewById(R.id.event_card_host_name)

        // Récupère les attributs
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EventCardView)

        // Affecte les attributs à chaques élément
        profileImageView.setImageDrawable(attributes.getDrawable(R.styleable.EventCardView_event_card_profile_image))
        eventTitle.text = attributes.getString(R.styleable.EventCardView_event_card_title)
        eventDate.text = attributes.getString(R.styleable.EventCardView_event_card_date)
        eventHour.text = attributes.getString(R.styleable.EventCardView_event_card_hour)
        eventDescription.text = attributes.getString(R.styleable.EventCardView_event_card_description)
        eventLocation.text = attributes.getString(R.styleable.EventCardView_event_card_location)
        eventLocation.text = attributes.getString(R.styleable.EventCardView_event_card_host_name)

        // Necessary for Android memory management
        attributes.recycle()
    }
}