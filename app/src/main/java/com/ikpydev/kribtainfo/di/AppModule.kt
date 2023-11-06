package com.ikpydev.kribtainfo.di

import com.ikpydev.kribtainfo.data.CoinPaprikApi
import com.ikpydev.kribtainfo.data.ConstataArgs
import com.ikpydev.kribtainfo.domain.CoinRepasitoryImpl
import com.ikpydev.kribtainfo.domain.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun getCoinPaprikApi(): CoinPaprikApi =
        Retrofit.Builder()
            .baseUrl(ConstataArgs.uri)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CoinPaprikApi::class.java)


    @Singleton
    @Provides
    fun getCoinRepastori(api: CoinPaprikApi):CoinRepository = CoinRepasitoryImpl(api)
}