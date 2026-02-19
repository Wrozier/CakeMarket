package com.example.cakemarket.remote
import com.example.cakemarket.model.CakeResponse
import com.example.cakemarket.model.CakeResponseItem
import retrofit2.http.GET
interface CakeApi {

    //make a REST call

    @GET(value="eat/cake.json")
    suspend fun getCake(): List<CakeResponseItem>

}