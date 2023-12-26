package com.example.petfinder.Coroutines

import androidx.annotation.VisibleForTesting
import androidx.test.espresso.IdlingResource

import androidx.test.espresso.idling.CountingIdlingResource


@VisibleForTesting
class MyIdlingResource {
    companion object {
        private val mCountingIdlingResource = CountingIdlingResource("my_idling_resource")
        fun increment() {
            mCountingIdlingResource.increment()
        }

        fun decrement() {
            mCountingIdlingResource.decrement()
        }

        fun getIdlingResource(): IdlingResource {
            return mCountingIdlingResource
        }
    }

}