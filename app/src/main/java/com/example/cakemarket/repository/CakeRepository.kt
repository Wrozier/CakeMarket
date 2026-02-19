package com.example.cakemarket.repository

import com.example.cakemarket.model.CakeResponseItem
import com.example.cakemarket.remote.RetrofitClient

class CakeRepository {

    suspend fun getCakes(): Result<List<CakeResponseItem>> = try {
        val cakes = RetrofitClient.api.getCake()
        Result.success(cakes)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
