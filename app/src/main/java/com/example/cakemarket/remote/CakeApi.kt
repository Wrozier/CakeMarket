package com.example.cakemarket.remote

import com.example.cakemarket.model.CakeResponseItem
import retrofit2.http.GET

//https://services.hanselandpetal.com/
interface CakeApi {

    //make a REST call

    @GET(value="hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json")
    suspend fun getCake(): List<CakeResponseItem>

}