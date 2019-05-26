package com.example.amaze.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.Toast
import com.example.amaze.R
import com.example.amaze.activities.CreateEventActivity.Companion.EVENT_CODE
import com.example.amaze.components.AmazeNextButton
import com.example.amaze.models.Organizer
import com.example.amaze.models.User
import com.example.amaze.network.*
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.activity_create_event.*
import java.util.*
import kotlin.collections.ArrayList

class CreateEventActivity : AppCompatActivity(), AmazeNextButton.OnNextButtonListener {

    private lateinit var event : SendableEvent

    companion object{
        val EVENT_CODE = "CREATED_EVENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)
        createEventNextButton.setNextButtonOnClickListener(this)



        event_creation_date.setOnClickListener {useDatePicker()}
    }


    fun Activity.hideKeyboard() {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getOrganizersFromAuthenticatedUser() : ArrayList<String>
    {
        val organizers = ArrayList<String>()
        // Si le token à bien été stocké on connecte l'utilisateur à l'activité principale
        val hostUser : AuthUser? = SecureStorageServices.authUser
        if (hostUser != null)
            organizers.add(hostUser.id)

        return organizers
    }

    fun useDatePicker() {

        // Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        hideKeyboard()
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view, mYear, mMonth, mDay ->
            event_creation_date.setText(""+mDay+"/"+mMonth+"/"+mYear)
        }, year, month, day)
        dpd.show()




    }

    override fun onNextButtonClick() {

        // Initialise event
        event = SendableEvent()

        event.title = event_creation_title.text.toString()
        event.date = event_creation_date.text.toString() // A CHANGER
        event.location = event_creation_location.text.toString()
        event.description = eventCreationDescription.text.toString()
        event.entrancePrice = Integer.parseInt(event_creation_price.text.toString())
        event.organizers = getOrganizersFromAuthenticatedUser()

        val intent = Intent(this@CreateEventActivity, AddGuestToEventActivity::class.java)
        intent.putExtra(EVENT_CODE, event)
        startActivity(intent)
    }


}
