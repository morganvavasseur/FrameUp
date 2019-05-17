package com.example.amaze.components

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import com.example.amaze.R
import kotlinx.android.synthetic.main.amaze_guests_component.view.*

class AmazeGuestsComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context)
            .inflate(R.layout.amaze_guests_component, this, true)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AmazeGuestsComponent_attrs,
            0, 0
        ).apply {
            try {
                amazeGuestsComming.text = getInt(R.styleable.AmazeGuestsComponent_attrs_guests_comming, 0).toString()
                amazeGuestsNotComming.text = getInt(R.styleable.AmazeGuestsComponent_attrs_guests_not_comming, 0).toString()
                amazeGuestsMaybe.text = getInt(R.styleable.AmazeGuestsComponent_attrs_guests_maybe, 0).toString()
            } catch (e: Error) { Log.d("ERRTOCHECK", e.message)}
            finally { recycle() }
        }
    }
}