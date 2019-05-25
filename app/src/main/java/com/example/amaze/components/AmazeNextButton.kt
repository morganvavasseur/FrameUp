/*
 * Developed by Yann Malanda on 5/25/19 4:23 PM.
 * Last modified 5/16/19 10:16 AM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.components

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import com.example.amaze.R
import kotlinx.android.synthetic.main.amaze_next_button.view.*

class AmazeNextButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    var onNextButtonListener : OnNextButtonListener? = null

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.amaze_next_button, this, true)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AmazeNextButton_attrs,
            0, 0
        ).apply {
            try {
                nextButtonText.text = getString(R.styleable.AmazeNextButton_attrs_text)
            } catch (e:Error) {
                Log.d("ERRTOCHECK", e.message)
            } finally {
                recycle()
            }
        }
    }

    fun setNextButtonOnClickListener(listener: OnNextButtonListener) {
        onNextButtonListener = listener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_UP -> onNextButtonListener?.onNextButtonClick()
        }
        return true
    }

    interface OnNextButtonListener{
        fun onNextButtonClick()
    }

}