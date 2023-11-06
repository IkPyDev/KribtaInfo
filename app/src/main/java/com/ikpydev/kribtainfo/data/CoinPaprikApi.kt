package com.ikpydev.kribtainfo.data

import com.ikpydev.kribtainfo.data.dto.CoinDetailModel
import com.ikpydev.kribtainfo.data.dto.CoinModel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface CoinPaprikApi {

    @GET("coins")
    suspend fun getCoinList():Response<List<CoinModel>>

    @GET("coins/{coinId}")
    suspend fun getCoinDetail(@Path("coinId") coinId:String):Response<CoinDetailModel>

}