package com.ikpydev.kribtainfo.prasentr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikpydev.kribtainfo.common.Resourse
import com.ikpydev.kribtainfo.common.UiEvent
import com.ikpydev.kribtainfo.domain.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(private val coinRepository: CoinRepository) :
    ViewModel() {
    private val _coinDetailObsarve = MutableStateFlow<UiEvent>(UiEvent.EmptyList)
    val coinDetailObsarve:StateFlow<UiEvent> get() = _coinDetailObsarve

    fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            _coinDetailObsarve.value = UiEvent.Loading
            when(val response = coinRepository.getCoinDetail(coinId)){
                is Resourse.Error -> _coinDetailObsarve.value = UiEvent.Error(response.message)
                is Resourse.Seccesss -> _coinDetailObsarve.value = UiEvent.Seccuss(response.data)
            }
        }
    }
}