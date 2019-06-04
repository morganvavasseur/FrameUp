/*
 * Developed by Yann Malanda on 6/3/19 12:09 PM.
 * Last modified 6/3/19 12:07 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.amaze.R
import com.example.amaze.adapters.FoundedPlacesItemAdapter
import com.example.amaze.components.AmazeNextButton
import com.example.amaze.models.Place
import com.example.amaze.network.AuthUser
import com.example.amaze.network.SendableEvent
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.amaze_event_description.view.*
import kotlinx.android.synthetic.main.fragment_event__params.*
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, onFoundedPlaceItemListener1.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EventParamsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EventParamsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EventParamsFragment : Fragment(), AmazeNextButton.OnNextButtonListener, FoundedPlacesItemAdapter.OnFoundedPlaceItemListener {

    // TODO: Rename and change types of parameters
    private var event: SendableEvent = SendableEvent()
    private var listener: OnEventParamsListener? = null
    lateinit var placesFragment: PlacesFragment
    var eventDate = ""
    var eventHour = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            event = it.getSerializable(ARG_PARAM1) as SendableEvent
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.amaze.R.layout.fragment_event__params, container, false)
    }

    override fun onStart() {
        super.onStart()

        placesFragment = PlacesFragment.newInstance(this)

        event_creation_date.setOnClickListener {useDatePicker()}
        event_creation_hour.setOnClickListener {useTimePicker()}
        event_creation_location.setOnClickListener { setFragment(placesFragment) }

        createEventNextButton.setNextButtonOnClickListener(this)

    }

    private fun setFragment(fragment: PlacesFragment, args : Place? = null) {
        if (args != null) {
            var arguments = Bundle()
            arguments.putSerializable("param2", args)
            fragment.arguments = arguments
        }

        var fragmentTransaction : FragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(com.example.amaze.R.id.placeFrameLayout, fragment)
        fragmentTransaction.commit()
        placeFrameLayout.visibility = View.VISIBLE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEventParamsListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun checkEventTitle() : Boolean {
        val testEventTitle= event_creation_title.text.isNotEmpty()
        if (!testEventTitle) {
            event_creation_title.setError(getString(R.string.new_event_no_title_error))
        }
        return testEventTitle
    }

    fun checkEventLocation() : Boolean {
        val testEventLocation = event_creation_location.text.isNotEmpty()
        if (!testEventLocation)
            event_creation_location.setError(getString(R.string.new_event_no_location_error))

        return testEventLocation
    }

    fun checkEventDate() : Boolean {
        val testEventDate = event_creation_date.text.isNotEmpty()
        if (!testEventDate)
            event_creation_date.setError(getString(R.string.new_event_no_date_error))
        return testEventDate
    }

    fun checkEventHour() : Boolean {
        val testEventHour = event_creation_hour.text.isNotEmpty()
        if (!testEventHour)
            event_creation_hour.setError(getString(R.string.new_event_no_date_error))
        return testEventHour
    }

    fun checkEventPrice() : Boolean {
        val testEventPrice = event_creation_price.text.isNotEmpty()
        if (!testEventPrice)
            event_creation_price.setError(getString(R.string.new_event_no_price_error))
        return testEventPrice
    }

    fun checkEventDescription() : Boolean {
        val testEventDescription = eventCreationDescription.text.isNotEmpty()
        if (!testEventDescription)
            eventCreationDescription.amazeDescriptionEditText.setError(getString(R.string.new_event_no_description_error))
        return testEventDescription
    }

    fun checkEventParams() : Boolean {
        if (!checkEventTitle())
            return false
        if (!checkEventLocation())
            return false
        if (!checkEventDate())
            return false
        if (!checkEventHour())
            return false
        if (!checkEventPrice())
            return false
        if (!checkEventDescription())
            return false
        return true
    }

    fun updateEventPlace(place: Place?) {
        if (place != null){
            event?.location = place
            event_creation_location.setText(place.formattedAddress)
        }
    }

    override fun onFoundedPlaceClick(clickedPlace: Place) {
        placeFrameLayout.visibility = View.GONE
        updateEventPlace(clickedPlace)
    }

    override fun onNextButtonClick() {

        if(!checkEventParams())
            return
        // Initialise event
        event?.title = event_creation_title.text.toString()
        event?.description = eventCreationDescription.text
        event?.entrancePrice = Integer.parseInt(event_creation_price.text.toString())
        event?.organizers = getHostFromAuthenticatedUser()
        event.date = eventDate + " " + eventHour

        listener?.onParamsDone(event!!)

    }

    fun getHostFromAuthenticatedUser() : ArrayList<String>
    {
        val host = ArrayList<String>()
        // Si le token à bien été stocké on connecte l'utilisateur à l'activité principale
        val hostUser : AuthUser? = SecureStorageServices.authUser
        if (hostUser != null)
            host.add(hostUser.id)

        return host
    }

    fun useDatePicker() {

        // Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener{
                view, mYear, mMonth, mDay ->
                event_creation_date.setText(""+adapteDaysNumber(mDay)+"/"+adapteDaysNumber(mMonth)+"/"+mYear)
                eventDate = "${adapteDaysNumber(mMonth)}/${adapteDaysNumber(mDay)}/$mYear"
        }, year, month, day)
        dpd.show()
    }

    fun useTimePicker() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(context,TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
            event_creation_hour.setText("${h}H$m")
            eventHour = "$h:$m"

        }),hour,minute,false)

        tpd.show()
    }

    fun setOnEventParamsListener(callback: OnEventParamsListener) {
        this.listener = callback
    }

    fun adapteDaysNumber(day : Int) : String {
        if (day <= 9)
            return "0"+day
        else
            return ""+day
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnEventParamsListener {
        fun onParamsDone(event: SendableEvent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventParamsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(event: SendableEvent) =
            EventParamsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, event)
                }
            }
    }
}
