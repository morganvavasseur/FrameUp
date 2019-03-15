package com.example.amaze.network

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.VolleyError
import com.example.amaze.AmazeApp
import com.example.amaze.models.Event
import com.neopixl.spitfire.listener.RequestListener
import com.neopixl.spitfire.request.BaseRequest

class Error(code:Int, message: String)

class EventService {
    companion object {

        fun getEvents(results: (results: List<Event>,
                                error: Error)-> Unit) {

            val url = UrlBuilder.getEvents()
            val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YzM5Y2M3ZGEyNDQyNTEwYzMyMzZjZGYiLCJpYXQiOjE1NDg5MzgxMjUsImV4cCI6MTU1MTUzMDEyNX0.yFknfrwLjskuqF-8gV67Hyjzzv3xzR-5lxlWAKDnZvs"

            // 3ème paramètre = Modèe de classe que l'on spécifie
            // au parser Json pour mapper le Json reçu à une classe donnée
            val request = BaseRequest.Builder<Array<Event>>(Request.Method.GET, url, Array<Event>::class.java)
                .headers(hashMapOf("Authorization" to "Bearer ${token}"))
                .listener(object: RequestListener<Array<Event>> {
                    override fun onSuccess(
                        request: Request<*>?,
                        response: NetworkResponse?,
                        result: Array<Event>?) {
                        if (result is Array<Event>){
                            // Ici result n'est pas nul

                        }
                    }

                    override fun onFailure(request: Request<*>?, response: NetworkResponse?, error: VolleyError?) {
                        error.toString()
                    }

                }).build() // Créer l'objet Request

            // Lancer la requête
            AmazeApp.sharedInstance.requestQueue.add(request)
        }
    }
}