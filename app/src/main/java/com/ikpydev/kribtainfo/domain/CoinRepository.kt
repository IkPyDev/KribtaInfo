package com.ikpydev.kribtainfo.domain

import com.ikpydev.kribtainfo.common.Resourse
import com.ikpydev.kribtainfo.data.dto.CoinDetailModel
import com.ikpydev.kribtainfo.data.dto.CoinModel

interface CoinRepository {

    suspend fun getCoinList(): Resourse<List<CoinModel>>

    suspend fun getCoinDetail(coinId: String): Resourse<CoinDetailModel>
}