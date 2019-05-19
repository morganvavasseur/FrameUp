package com.example.amaze.components

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.amaze.R

class AmazeNoteComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    init {
        LayoutInflater.from(context)
            .inflate(R.layout.amaze_note_component, this, true)
    }
}