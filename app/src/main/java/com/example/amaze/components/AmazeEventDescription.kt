/*
 * Developed by Yann Malanda on 5/25/19 4:23 PM.
 * Last modified 5/19/19 1:40 AM
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
import java.lang.Error

class AmazeEventDescription @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var isEditable : Boolean = false
    var text : String = ""

    fun AmazeEventDescription.setText(text : String) {
        this.text = text
    }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.amaze_event_description, this, true)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AmazeEventDescription_attrs,
            0, 0
        ).apply {
            try {
                amazeDescriptionText.text = getString(R.styleable.AmazeEventDescription_attrs_description_text)
                amazeDescriptionEditText.setText(getString(R.styleable.AmazeEventDescription_attrs_description_text))
                isEditable = getBoolean(R.styleable.AmazeEventDescription_attrs_description_is_editable, false)
                changeState()
            }
            catch (e:Error) { Log.d("ERRTOCHECK", e.message)}
            finally { recycle() }
        }
    }

    fun changeState(){
        when(isEditable){
            true -> {
                amazeDescriptionText.visibility = View.GONE
                amazeDescriptionEditText.visibility = View.VISIBLE
            }
            false -> {
                amazeDescriptionText.visibility = View.VISIBLE
                amazeDescriptionEditText.visibility = View.GONE
            }
        }
    }
}