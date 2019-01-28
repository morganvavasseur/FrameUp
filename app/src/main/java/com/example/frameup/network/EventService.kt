package com.example.frameup.network

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.VolleyError
import com.example.frameup.AmazeApp
import com.example.frameup.models.Event
import com.neopixl.spitfire.listener.RequestListener
import com.neopixl.spitfire.request.BaseRequest

class Error(code:Int, message: String)

class EventService {
    companion object {

        fun getEvents(results: (results: Array<Event>,
                                  error: Error)-> Unit) {

            val url = UrlBuilder.getEvents()


            // 3ème paramètre = Modèe de classe que l'on spécifie
            // au parser Json pour mapper le Json reçu à une classe donnée
            val request = BaseRequest.Builder<Event>(Request.Method.GET, url, Event::class.java)
                .listener(object: RequestListener<Event> {
                    override fun onSuccess(
                        request: Request<Event>,
                        response: NetworkResponse,
                        result: Array<Event>?
                    ) {
                        if (result is Array<Event>){
                            // Ici result n'est pas nul

                        }
                    }

                    override fun onFailure(
                        request: Request<Event>,
                        response: NetworkResponse?,
                        error: VolleyError?
                    ) {
                        error.toString()
                    }

                }).build() // Créer l'objet Request

            // Lancer la requête
            AmazeApp.sharedInstance.requestQueue.add(request)
        }
    }
}