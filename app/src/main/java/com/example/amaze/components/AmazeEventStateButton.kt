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
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.SendableEvent
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.amaze_guests_component.view.*
import kotlinx.android.synthetic.main.amaze_state_button.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AmazeEventStateButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var state = 1
    lateinit var event : SendableEvent
    var onEventStateListener : OnEventStateListener? = null

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
//                    stateButton.setOnClickListener({onStateButtonClick()})
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
                stateButton.text = resources.getText(R.string.comming_text)
            }
            // Si l'utilisateur avait dis ne pas savoir si il venait
            State.MAYBE -> {
                stateButton.text = resources.getText(R.string.maybe_text)
            }
            // Si l'utilisateur a dis ne pas venir
            State.NOT_COMMING -> {
                stateButton.text = resources.getText(R.string.not_comming_text)
            }

        }
    }

    fun onStateButtonClick() {
        // 1 - On vire l'utilisateur de toutes les listes de la soirée
        exitAuthUserFromEachStateList()
        // 2 - On l'ajoute dans la bonne liste en fonction du bouton clicker
        when(state){
            0 -> event.guestsComming.add(SecureStorageServices.authUser!!.id)
            1 -> event.guestsMaybe.add(SecureStorageServices.authUser!!.id)
            2 -> event.guestsNotComming.add(SecureStorageServices.authUser!!.id)
        }
        // 3 - On envoit l'event mis à jour à la base de donnée
        val updateEventRequest = RetrofitClient.eventService.updateEvent(event.id, event)

        updateEventRequest.enqueue(object : Callback<EventResult> {
            override fun onFailure(call: Call<EventResult>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<EventResult>, response: Response<EventResult>) {
                var eventResult = response.body()
                if (eventResult is EventResult)
                    onEventStateListener?.onEventStateChanged(eventResult, this@AmazeEventStateButton)
            }

        })
    }

    fun exitAuthUserFromEachStateList(){

        event.guestsComming = event.guestsComming.filter { id -> id != SecureStorageServices.authUser?.id } as ArrayList<String>
        event.guestsMaybe = event.guestsMaybe.filter { id -> id != SecureStorageServices.authUser?.id } as ArrayList<String>
        event.guestsNotComming = event.guestsNotComming.filter { id -> id != SecureStorageServices.authUser?.id } as ArrayList<String>
    }

    fun setOnEventStateListen(listener : OnEventStateListener) {
        onEventStateListener = listener
    }

    interface OnEventStateListener {
        fun onEventStateChanged(eventChanged : EventResult, stateButton : AmazeEventStateButton)
    }

}