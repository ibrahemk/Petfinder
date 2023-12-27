package com.example.petfinder.Retrofitapis

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


class APIClient {
//    .addConverterFactory(ToStringConverterFactory())
//    .addConverterFactory(GsonConverterFactory.create())
    companion object {
        private var retrofit: Retrofit? = null
        @JvmStatic
        fun getClient(url: String): Retrofit? {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            retrofit = Retrofit.Builder()

                .baseUrl(url)

                .addConverterFactory(ToStringConverterFactory())

                .client(client)
                .build()
            return retrofit
        }
    }

}