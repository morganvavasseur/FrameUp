/*
 * Developed by Yann Malanda on 5/25/19 4:23 PM.
 * Last modified 5/25/19 4:23 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.components

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.amaze.R
import kotlinx.android.synthetic.main.amaze_state_button.view.*


class AmazeEventStateButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var state = 1

    // Les 3 états possible pour un state button
    enum class State {
        COMMING,
        MAYBE,
        NOT_COMMING
    }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.amaze_state_button, this, true)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AmazeEventStateButton_attrs,
            0, 0)
            .apply {
                try {
                    state = getInteger(R.styleable.AmazeEventStateButton_attrs_state, 0)
                } catch (e: Error) {
                    error(e)
                }
                finally {
                    recycle()
                }
            }

        when(state) {
            0 -> setState(State.COMMING)
            1 -> setState(State.MAYBE)
            2 -> setState(State.NOT_COMMING)
        }
    }

    fun setState(state: State) {
        when(state){
            // Si l'utilisateur à dis qu'il venait
            State.COMMING -> {
                button.text = resources.getText(R.string.comming_text)
                button.background = resources.getDrawable(R.drawable.comming_button)
            }
            // Si l'utilisateur avait dis ne pas savoir si il venait
            State.MAYBE -> {
                button.text = resources.getText(R.string.maybe_text)
                button.background = resources.getDrawable(R.drawable.maybe_button)
            }
            // Si l'utilisateur a dis ne pas venir
            State.NOT_COMMING -> {
                button.text = resources.getText(R.string.not_comming_text)
                button.background = resources.getDrawable(R.drawable.not_comming_button)
            }

        }
    }

}