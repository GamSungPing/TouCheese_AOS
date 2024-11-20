package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.rule.Region
import com.example.presentation.R
import com.example.presentation.databinding.FragmentResultViewBinding
import com.example.presentation.databinding.PriceFilterPopupBinding
import com.example.presentation.databinding.RegionFilterPopupBinding
import com.example.presentation.sample.ResultViewAdapter
import com.example.presentation.sample.ResultViewModel
import com.example.presentation.sample.Studio
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultViewFragment : Fragment(R.layout.fragment_result_view) {

    private var _priceFilterBinding: PriceFilterPopupBinding? = null
    private val priceFilterBinding get() = _priceFilterBinding!!

    private var _regionFilterBinding: RegionFilterPopupBinding? = null
    private val regionFilterBinding get() = _regionFilterBinding!!

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _priceFilterBinding = PriceFilterPopupBinding.inflate(layoutInflater)
        _regionFilterBinding = RegionFilterPopupBinding.inflate(layoutInflater)
        val binding = FragmentResultViewBinding.bind(view)

        setupRvStudioList(binding)
        setupPriceFilterPopup(binding)
        setupRegionFilterPopup(binding)

        observePriceViewModel()
        observeRegionViewModel()

        resultViewAdapter.submitList(sampleData)
    }

    override fun onDestroyView() {
        _priceFilterBinding = null
        super.onDestroyView()
    }

    private fun setupRvStudioList(binding: FragmentResultViewBinding) {
        resultViewAdapter = ResultViewAdapter()
        binding.rvStudioList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = resultViewAdapter
        }

    }

    private fun setupPriceFilterPopup(binding: FragmentResultViewBinding) {
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

    private fun setupRegionFilterPopup(binding: FragmentResultViewBinding) {

        val popupWindow = PopupWindow(
            regionFilterBinding.root,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        binding.btFilterRegion.apply {
            setOnClickListener {
                popupWindow.showAsDropDown(this)

                regionFilterBinding.apply {
                    btRegion1.setOnCheckedChangeListener { _, isChecked ->
                        viewModel.setSelectedRegion(Region.Gangnam)
                    }
                    btRegion2.setOnCheckedChangeListener { _, isChecked ->
                        //viewModel.setSelectedRegion(btRegion2.id, isChecked )
                    }
                    btRegion3.setOnCheckedChangeListener { _, isChecked ->
                        //viewModel.setSelectedRegion(btRegion3.id, isChecked )
                    }
                    btRegion4.setOnCheckedChangeListener { _, isChecked ->
                        //viewModel.setSelectedRegion(btRegion4.id, isChecked )
                    }
                    btRegion5.setOnCheckedChangeListener { _, isChecked ->
                        //viewModel.setSelectedRegion(btRegion5.id, isChecked )
                    }
                    btRegion6.setOnCheckedChangeListener { _, isChecked ->
                        //viewModel.setSelectedRegion(btRegion6.id, isChecked )
                    }
                    btRegion7.setOnCheckedChangeListener { _, isChecked ->
                        //viewModel.setSelectedRegion(btRegion7.id, isChecked )
                    }
                    btRegion8.setOnCheckedChangeListener { _, isChecked ->
                        //viewModel.setSelectedRegion(btRegion8.id, isChecked )
                    }
                    btRegion9.setOnCheckedChangeListener { _, isChecked ->
                        //viewModel.setSelectedRegion(btRegion9.id, isChecked )
                    }
                }
        }   }
    }

    private fun observePriceViewModel() {
        viewModel.selectedButton.observe(viewLifecycleOwner) { selectedButton ->
            priceFilterBinding.apply {
                btPriceAll.isChecked = (selectedButton == "btPriceAll")
                btPrice1.isChecked = (selectedButton == "btPrice1")
                btPrice2.isChecked = (selectedButton == "btPrice2")
                btPrice3.isChecked = (selectedButton == "btPrice3")
            }
        }
    }


    private fun observeRegionViewModel() {
        viewModel.selectedRegion.observe(viewLifecycleOwner) { checkedBox ->
            regionFilterBinding.apply {
            }
        }
    }
}