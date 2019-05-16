package com.example.amaze.components

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import kotlinx.android.synthetic.main.amaze_long_button.view.*
import java.lang.Error

class AmazeLongButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    
    init {
        LayoutInflater.from(context)
            .inflate(R.layout.amaze_long_button, this as ViewGroup, true)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AmazeLongButton,
            0, 0
        ).apply {
            try {
                amazeLongButton.text = getString(R.styleable.AmazeLongButton_button_text)
            } catch (e: Error){

            }
            finally {
                recycle()
            }
        }

    }

}