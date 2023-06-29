package com.propertysearchassignment.app.network

import com.propertysearchassignment.app.model.data.DataResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("ad-assignment/db")
    fun getData(): Observable<DataResponse>
}