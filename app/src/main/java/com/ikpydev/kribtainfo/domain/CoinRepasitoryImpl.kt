package com.ikpydev.kribtainfo.domain

import android.util.Log
import com.ikpydev.kribtainfo.common.Resourse
import com.ikpydev.kribtainfo.data.CoinPaprikApi
import com.ikpydev.kribtainfo.data.dto.CoinDetailModel
import com.ikpydev.kribtainfo.data.dto.CoinModel
import javax.inject.Inject

class CoinRepasitoryImpl @Inject constructor(val api: CoinPaprikApi) : CoinRepository {
    override suspend fun getCoinList(): Resourse<List<CoinModel>> {
        return try {

            val response = api.getCoinList()

            if (response.isSuccessful && response.body() != null
            ) {
                Resourse.Seccesss(response.body())
            } else {
                Resourse.Error(message = response.message())
            }

        } catch (
            e: Exception
        ) {
            Resourse.Error(message = e.message)
        }
    }

    override suspend fun getCoinDetail(coinId: String): Resourse<CoinDetailModel> {
        return try {

            val response = api.getCoinDetail(coinId)

            if (response.isSuccessful && response.body() != null
            ) {
                Resourse.Seccesss(response.body())
            } else {
                Resourse.Error(message = response.message())
            }

        } catch (
            e: Exception
        ) {
            Resourse.Error(message = e.message)
        }
    }
}