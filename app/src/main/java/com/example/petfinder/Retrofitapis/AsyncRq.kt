package com.example.petfinder.Retrofitapis


import androidx.annotation.MainThread
import com.example.petfinder.Coroutines.MyIdlingResource


import kotlinx.coroutines.*



 abstract class AsyncRq<T,Params>() {

    private var status = Status.PENDING
    protected open fun onPreExecute(){}
    protected abstract fun doInBackground(vararg Param: Params): T
    protected open fun onPostExecute(result: T){}
    val scope = CoroutineScope(context = Dispatchers.IO)



    @MainThread
    fun <R>execute(vararg Param: Params): AsyncRq<T, Params> {



        scope.async {


            status = Status.RUNNING
            withContext(Dispatchers.Main) {

                onPreExecute()

                MyIdlingResource.increment();

            }
            val result = withContext(Dispatchers.IO) { // runs in background thread without blocking the Main Thread
                doInBackground(*Param)
            }
            withContext(Dispatchers.Main){
                onPostExecute(result)
                MyIdlingResource.decrement();
            }

            status = Status.FINISHED
        }
        return this
    }

    fun cancel() {
        if (scope!=null){
            status = Status.FINISHED
            scope.cancel()

        }

    }
    fun getStatus(): Status {
        return status
    }



     enum class Status {
        /**
         * Indicates that the task has not been executed yet.
         */
        PENDING,

        /**
         * Indicates that the task is running.
         */
        RUNNING,

        /**
         * Indicates that [AsyncTask.onPostExecute] has finished.
         */
        FINISHED
    }


}