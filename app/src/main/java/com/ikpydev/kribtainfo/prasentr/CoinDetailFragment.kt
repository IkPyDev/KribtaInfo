package com.ikpydev.kribtainfo.prasentr

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ikpydev.kribtainfo.R
import com.ikpydev.kribtainfo.common.UiEvent
import com.ikpydev.kribtainfo.data.ConstataArgs
import com.ikpydev.kribtainfo.data.dto.CoinDetailModel
import com.ikpydev.kribtainfo.databinding.CoinDetailLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {
    val viewModel: CoinDetailViewModel by viewModels()
    private var _binding: CoinDetailLayoutBinding? = null
    val binding get() = _binding!!
    val TAG = "TAG"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CoinDetailLayoutBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coinid = arguments?.getString(ConstataArgs.coinid)
        if (coinid != null) {
            lifecycleScope.launchWhenCreated {
                viewModel.coinDetailObsarve.collectLatest {
                    when (it) {
                        UiEvent.EmptyList -> Unit
                        is UiEvent.Error -> {

                            binding.CoinDetail.isVisible = false
                            binding.coinProgesbar.isVisible = false
                            binding.coinErrorTv.isVisible = true
                            binding.coinErrorTv.text = it.message

                        }

                        UiEvent.Loading -> {
                            binding.coinProgesbar.isVisible = true
                            binding.CoinDetail.isVisible = false
                            binding.coinErrorTv.isVisible = false

                        }

                        is UiEvent.Seccuss<*> -> {
                            binding.CoinDetail.isVisible = true
                            binding.coinProgesbar.isVisible = false
                            binding.coinErrorTv.isVisible = false
                            populateData(it.data as CoinDetailModel)
                        }
                    }
                }
            }
            viewModel.getCoinDetail(coinid)

        }
    }

    private fun populateData(coinDetail: CoinDetailModel) {
        Glide.with(requireContext()).load(coinDetail.logo).into(binding.CoinDatailImage)




        binding.CoinDatailName.text = "${coinDetail.rank} ${coinDetail.name} (${coinDetail.symbol})"
        binding.CoinDatailstatus.text = if (coinDetail.is_active) "Active" else "No active"
        binding.CoinDatailstatus.setTextColor(if (coinDetail.is_active) Color.GREEN else Color.RED)
        coinDetail.description?.apply {
            binding.CoinDataildesciption.text = this

        }
        if (coinDetail.description.isNullOrEmpty()){
            binding.CoinDataildesciption.text = "Information Not Found"
        }


        if (!coinDetail.tags.isNullOrEmpty()){
            coinDetail.tags.forEach{
                binding.FlowLayout.addView(getTagTextView(it.name))
            }
        }
    }

    private fun getTagTextView(tagString: String): TextView {
        val textView = TextView(requireContext())
        textView.text = tagString
        textView.setTextColor(Color.GREEN)
        textView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textView.setPadding(30,20,30,20)
        textView.setBackgroundResource(R.drawable.bg_tag)
        return textView
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}