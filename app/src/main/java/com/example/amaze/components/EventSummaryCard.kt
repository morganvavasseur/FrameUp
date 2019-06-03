/*
 * Developed by Yann Malanda on 5/25/19 4:23 PM.
 * Last modified 5/16/19 11:28 AM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.components

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.example.amaze.R
import kotlinx.android.synthetic.main.event_summary_card.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class EventSummaryCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), TextWatcher {


    init {
        LayoutInflater.from(context)
            .inflate(R.layout.event_summary_card, this, true)

        amazeSummaryPrice.addTextChangedListener(this)
        amazeSummaryPriceRb.setOnClickListener({amazeSummaryPrice.setText("0")})
        amazeSummaryPrice.setOnFocusChangeListener { view, b ->
            if (!b){
                amazeSummaryPrice.setText("0")
            }
        }


    }


    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        when(s.hashCode()) {
            amazeSummaryPrice.text.hashCode() -> {
                if (!amazeSummaryPrice.text.toString().isEmpty()){
                    if (amazeSummaryPrice.text.toString().toInt() == 0)
                        amazeSummaryPriceRb.isChecked = true
                    else amazeSummaryPriceRb.isChecked = false
                } else
                    amazeSummaryPriceRb.isChecked = true

            }
        }
    }


}