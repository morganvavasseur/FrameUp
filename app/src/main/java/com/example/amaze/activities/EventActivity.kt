package com.example.amaze.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.amaze.R
import com.example.amaze.adapters.FoundedPlacesItemAdapter
import com.example.amaze.fragments.PlacesFragment
import com.example.amaze.fragments.GuestsStateFragment
import com.example.amaze.models.Place
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.SendableEvent
import com.example.amaze.utils.EventSupportFunctions
import com.example.amaze.utils.ExtraStrings
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.amaze_guests_component.*
import kotlinx.android.synthetic.main.amaze_guests_component.view.*
import kotlinx.android.synthetic.main.event_summary_card.*
import kotlinx.android.synthetic.main.event_summary_card.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EventActivity : AppCompatActivity(), FoundedPlacesItemAdapter.OnFoundedPlaceItemListener, PlacesFragment.OnPlacesFragmentListener  {


    lateinit var event: EventResult
    var isHostedByAuthUser : Boolean = false
    var inEditMode = false
    lateinit var placesFragment: PlacesFragment
    var eventDate : String = ""
    var eventHour : String = ""
    lateinit var guestsStateFragment : GuestsStateFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        placesFragment = PlacesFragment.newInstance(this)


        event = intent.extras.getSerializable(ExtraStrings.EXTRA_EVENT) as EventResult
        isHostedByAuthUser = intent.extras.getBoolean(ExtraStrings.EXTRA_IS_OWNER)

        updateEventInformations()

        if(!isHostedByAuthUser)
            eventEditButton.visibility = View.GONE

        displayEventInformations()
        adaptLayoutIfEditable()

        guestsStateFragment = GuestsStateFragment.newInstance(event)

        eventDate = getEventDate(ExtraStrings.EVENT_ACTIVITY_DATE_FORMAT)
        eventHour = getEventDate(ExtraStrings.EVENT_SUMMARY_HOUR_FORMAT)

        eventEditButton.setOnClickListener({onEventEditButtonClick()})
        eventSummaryCard.eventSummaryCardAddress.setOnClickListener{onEventLocationTvClick()}
        eventSummaryCard.eventSummaryCardDate.setOnClickListener{useDatePicker()}
        eventSummaryCard.eventSummaryCardHour.setOnClickListener{useTimePicker()}
        amazeGuestsComponent.guestsComponentButton.setOnClickListener {onGuestComponentClick()}
    }

    fun onEventLocationTvClick() {
        if(inEditMode)
            setFragment(placesFragment)
    }

    // Formatte la date pour une date adaptée a l'event card
    fun getEventDate(toFormat : String = ExtraStrings.EVENT_SUMMARY_DATE_FORMAT) : String {
        val date = EventSupportFunctions.convertInstantStringDateToLocaleDateTime(event.date)
        val formatter =  SimpleDateFormat(toFormat)
        return formatter.format(date)
    }

    fun updateEventInformations() {

        val getEventRequest = RetrofitClient.eventService.getEvent(event.id)

        getEventRequest.enqueue(object : Callback<EventResult> {
            override fun onFailure(call: Call<EventResult>, t: Throwable) {
                Toast.makeText(this@EventActivity, "Error when trying to get event informations", Toast.LENGTH_LONG).show()
                finish()
            }

            override fun onResponse(call: Call<EventResult>, response: Response<EventResult>) {
                var responseEvent = response.body()
                if(responseEvent is EventResult) {
                    event = responseEvent
                    displayEventInformations()

                }
            }
        })
    }

    fun displayEventInformations() {
        eventSummaryCard.eventSummaryCardTitle.setText(event.title)
        eventSummaryCard.eventSummaryCardHostName.setText(EventSupportFunctions.getHost(event)?.fullName())
        eventSummaryCard.eventSummaryCardDescription.setText(event.description)
        eventSummaryCard.eventSummaryCardDate.setText(getEventDate())
        eventSummaryCard.eventSummaryCardHour.setText(getEventDate(ExtraStrings.EVENT_SUMMARY_HOUR_FORMAT))
        eventSummaryCard.eventSummaryCardAddress.setText(event.location.formattedAddress)
        eventSummaryCard.amazeSummaryPrice.setText(event.entrancePrice.toString())

        amazeGuestsComponent.amazeGuestsComming.text = event.guestsComming.count().toString()
        amazeGuestsComponent.amazeGuestsNotComming.text = event.guestsNotComming.count().toString()
        amazeGuestsComponent.amazeGuestsMaybe.text = event.guestsMaybe.count().toString()
    }

    fun onGuestComponentClick() {
        setFragment(guestsStateFragment)
    }

    fun onEventEditButtonClick() {
        // 1 - Si on est en mode edit on sauvegarde et on envoie a la base de donnée
        if(inEditMode){
            event.title = eventSummaryCardTitle.text.toString()
            event.description = eventSummaryCardDescription.text.toString()
            event.entrancePrice = eventSummaryCard.amazeSummaryPrice.text.toString().toInt()
            event.date = "$eventDate $eventHour"

            val updateEventRequest = RetrofitClient.eventService.updateEvent(event.id, SendableEvent(event))

            updateEventRequest.enqueue(object : Callback<EventResult> {
                override fun onFailure(call: Call<EventResult>, t: Throwable) {
                    Toast.makeText(this@EventActivity, "Error to save event", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<EventResult>, response: Response<EventResult>) {
                    var eventResult = response.body()
                    if (eventResult is EventResult)
                        Toast.makeText(this@EventActivity, "Birthday updated", Toast.LENGTH_SHORT).show()
                    // 2 - Après et sinon on change le layout
                    inEditMode = !inEditMode
                    adaptLayoutIfEditable()
                }

            })
        }
        else {
            inEditMode = !inEditMode
            adaptLayoutIfEditable()
        }
    }

    fun changeEditTextState(editText: EditText, isEditable: Boolean) {
        if(isEditable) {
            editText.isFocusable = inEditMode
            editText.isFocusableInTouchMode = inEditMode
        }

        if (inEditMode)
            editText.background = getDrawable(R.drawable.abc_edit_text_material)
        else
            editText.background = ColorDrawable(ContextCompat.getColor(this, R.color.transparent))
    }

    fun adaptLayoutIfEditable() {
        changeEditTextState(eventSummaryCard.eventSummaryCardTitle, true)
        changeEditTextState(eventSummaryCard.eventSummaryCardDescription, true)
        changeEditTextState(eventSummaryCard.amazeSummaryPrice, true)
        changeEditTextState(eventSummaryCard.eventSummaryCardAddress, false)
        changeEditTextState(eventSummaryCard.eventSummaryCardDate, false)
        changeEditTextState(eventSummaryCard.eventSummaryCardHour, false)

        if(inEditMode) {
            eventEditButton.setText(R.string.save)
            eventSummaryCard.amazeSummaryPriceRb.visibility = View.VISIBLE
        } else {
            eventEditButton.setText(R.string.profile_edit)
            eventSummaryCard.amazeSummaryPriceRb.visibility = View.INVISIBLE
        }
    }

    private fun setFragment(fragment: Fragment) {
        var fragmentTransaction : FragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.eventLocationFrag, fragment)
        fragmentTransaction.commit()
        eventLocationFrag.visibility = View.VISIBLE
    }

    override fun onFoundedPlaceClick(clickedPlace: Place) {
        eventLocationFrag.visibility = View.GONE
        event.location = clickedPlace
        eventSummaryCard.eventSummaryCardAddress.setText(event.location.formattedAddress)
    }

    fun useDatePicker() {

        if (!inEditMode)
            return
        // Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view, mYear, mMonth, mDay ->
            eventSummaryCard.eventSummaryCardDate.setText(""+adapteDaysNumber(mDay)+"/"+adapteDaysNumber(mMonth)+"/"+mYear)
            eventDate = "${adapteDaysNumber(mMonth)}/${adapteDaysNumber(mDay)}/$mYear"
        }, year, month, day)
        dpd.show()
    }

    fun useTimePicker() {

        if(!inEditMode)
            return

        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
            eventSummaryCard.eventSummaryCardHour.setText("${h}H$m")
            eventHour = "$h:$m"

        }),hour,minute,false)

        tpd.show()
    }

    fun adapteDaysNumber(day : Int) : String {
        if (day <= 9)
            return "0"+day
        else
            return ""+day
    }


    override fun onPlaceSelected(selectedPlace: Place) {
    }
}
