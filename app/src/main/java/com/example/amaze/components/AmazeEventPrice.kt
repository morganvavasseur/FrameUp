/*
 * Developed by Yann Malanda on 5/25/19 4:23 PM.
 * Last modified 5/19/19 12:42 AM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.components

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.amaze.R
import kotlinx.android.synthetic.main.amaze_event_description.view.*
import kotlinx.android.synthetic.main.amaze_event_price.view.*
import java.lang.Error

class AmazeEventPrice @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var isEditable : Boolean = false
    var value : Number = 0f

    fun AmazeEventPrice.setValue(text : Number) {
        value = text
    }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.amaze_event_price, this, true)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AmazeEventPrice_attrs,
            0, 0
        ).apply {
            try {
                isEditable = getBoolean(R.styleable.AmazeEventPrice_attrs_price_is_editable, false)
                amazeEventPriceEditText.setText(getString(R.styleable.AmazeEventPrice_attrs_price_value))
                amazeEventPriceText.text = getString(R.styleable.AmazeEventPrice_attrs_price_value)
                if (value != null) {
                    amazeEventPriceEditText.setText(value.toString())
                    amazeEventPriceText.text = value.toString()
                }

                changeState()
            } catch (e:Error) { Log.d("ERRTOCHECK", e.message) }
            finally { recycle() }
        }
    }


    fun changeState() {
        when(isEditable){
            true -> {
                amazeEventPriceEditText.visibility = View.VISIBLE
                amazeEventPriceText.visibility = View.GONE
            }
            false -> {
                amazeEventPriceEditText.visibility = View.GONE
                amazeEventPriceText.visibility = View.VISIBLE
            }
        }
    }
}