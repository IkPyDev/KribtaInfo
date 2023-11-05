package com.ikpydev.kribtainfo.prasentr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ikpydev.kribtainfo.R
import com.ikpydev.kribtainfo.common.UiEvent
import com.ikpydev.kribtainfo.databinding.FragmentCoinListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CoinListFragment : Fragment() {
    private var _bingding: FragmentCoinListBinding? = null
    private val binding get() = _bingding!!
    private val viewModel: CoinListViewModel by viewModels()
    val TAG = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCoinList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bingding = FragmentCoinListBinding.inflate(layoutInflater, container, false)
        return _bingding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("DEPRECATION")
        lifecycleScope.launchWhenCreated {
            viewModel.coinListObserver.collectLatest {
                when (it) {
                    UiEvent.EmptyList -> Unit
                    is UiEvent.Error -> {
                        binding.progressBar.isVisible = false
                        binding.errorTv.isVisible = true
                        binding.errorTv.text = it.message
                        binding.RecyclerView.isVisible = false
                    }

                    UiEvent.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.RecyclerView.isVisible = false
                        binding.errorTv.isVisible = false

                    }
                    is UiEvent.Seccuss<*> -> {
                        Log.d(TAG, "onViewCreated: ${it.data}")
                    }
                }
            }
        }
    }


}