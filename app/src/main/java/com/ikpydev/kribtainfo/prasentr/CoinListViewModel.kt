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
class CoinListViewModel @Inject constructor(private val coinRepository: CoinRepository) :
    ViewModel() {
    private val _coinListObserver = MutableStateFlow<UiEvent>(UiEvent.EmptyList)
    val coinListObserver: StateFlow<UiEvent> get() = _coinListObserver
    fun getCoinList() {
        viewModelScope.launch {
            _coinListObserver.value = UiEvent.Loading
            val resuorse = coinRepository.getCoinList()
            when (resuorse) {
                is Resourse.Error -> {
                    _coinListObserver.value = UiEvent.Error(
                        resuorse.message

                    )
                }

                is Resourse.Seccesss -> {
                    _coinListObserver.value = UiEvent.Seccuss(resuorse.data)


                }
            }
        }
    }


}