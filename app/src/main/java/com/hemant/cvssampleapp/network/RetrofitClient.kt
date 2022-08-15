package com.hemant.cvssampleapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val feedApi: FeedApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(FeedApi.BASE_URL)
        .build()
        .create(FeedApi::class.java)

}