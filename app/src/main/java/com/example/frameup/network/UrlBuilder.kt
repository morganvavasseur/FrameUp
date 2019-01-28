package com.example.frameup.network

class UrlBuilder {

    companion object {
        var production = false
        private val apiKey = "Not Yet"

        val baseUrl: String by lazy {
            if(production) {
                "NO URI YET"

            } else {
                "http://localhost:1337/"
            }
        }

        fun getEvents() : String {
            return "${baseUrl}/events"
        }

    }


}