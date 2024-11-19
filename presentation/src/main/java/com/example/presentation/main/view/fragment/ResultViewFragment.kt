package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.databinding.FragmentResultViewBinding
import com.example.presentation.databinding.PriceFilterPopupBinding
import com.example.presentation.databinding.RegionFilterPopupBinding
import com.example.presentation.sample.ResultViewAdapter
import com.example.presentation.sample.ResultViewModel
import com.example.presentation.sample.Studio

class ResultViewFragment : Fragment() {
    private var _binding : FragmentResultViewBinding? = null
    private val binding get() = _binding!!

    private var _priceFilterBinding: PriceFilterPopupBinding? = null
    private val priceFilterBinding get() = _priceFilterBinding!!

    private lateinit var resultViewAdapter: ResultViewAdapter
    private val viewModel: ResultViewModel by viewModels()

    // 임시 데이터
    val sampleData = listOf(
        Studio(id = 1, name = "Studio 1", listOf(
            "image1",
            "image2",
            "image3",
            "image4",
            "image5",
        )),
        Studio(id = 2, name = "Studio 2", listOf(
            "image1",
            "image2",
            "image3",
            "image4",
            "image5",
        )),
        Studio(id = 3, name = "Studio 3", listOf(
            "image1",
            "image2",
            "image3",
            "image4",
            "image5",
        )),
        Studio(id = 4, name = "Studio 4", listOf(
            "image1",
            "image2",
            "image3",
            "image4",
            "image5",
        ))
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultViewBinding.inflate(inflater, container, false)
        _priceFilterBinding = PriceFilterPopupBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvStudioList()
        setupPriceFilterPopup()
        setupRegionFilterPopup()

        observeViewModel()

        resultViewAdapter.submitList(sampleData)
    }

    override fun onDestroyView() {
        _binding = null
        _priceFilterBinding = null
        super.onDestroyView()
    }

    private fun setupRvStudioList() {
        resultViewAdapter = ResultViewAdapter()
        binding.rvStudioList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = resultViewAdapter
        }
    }

    private fun setupPriceFilterPopup() {
        val popupWindow = PopupWindow(
            priceFilterBinding.root,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        binding.btFilterPrice.apply {
            setOnClickListener {
                popupWindow.showAsDropDown(this)

                priceFilterBinding.apply {
                    btPriceAll.setOnClickListener {
                        viewModel.setSelectedButton("btPriceAll")
                    }
                    btPrice1.setOnClickListener {
                        viewModel.setSelectedButton("btPrice1")
                    }
                    btPrice2.setOnClickListener {
                        viewModel.setSelectedButton("btPrice2")
                    }
                    btPrice3.setOnClickListener {
                        viewModel.setSelectedButton("btPrice3")
                    }
                }
            }
        }
    }

    private fun setupRegionFilterPopup() {
        val popupBinding = RegionFilterPopupBinding.inflate(layoutInflater)

        val popupWindow = PopupWindow(
            popupBinding.root,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        binding.btFilterRegion.apply {
            setOnClickListener {
                popupWindow.showAsDropDown(this)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.selectedButton.observe(viewLifecycleOwner) { selectedButton ->
            priceFilterBinding.apply {
                btPriceAll.isChecked = (selectedButton == "btPriceAll")
                btPrice1.isChecked = (selectedButton == "btPrice1")
                btPrice2.isChecked = (selectedButton == "btPrice2")
                btPrice3.isChecked = (selectedButton == "btPrice3")
            }
        }
    }
}