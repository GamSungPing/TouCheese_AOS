package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region
import com.example.presentation.R
import com.example.presentation.databinding.FragmentResultViewBinding
import com.example.presentation.databinding.PriceFilterPopupBinding
import com.example.presentation.databinding.RegionFilterPopupBinding
import com.example.presentation.main.view.adapter.ResultViewAdapter
import com.example.presentation.main.vm.HomeConceptViewModel
import com.example.presentation.main.vm.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultViewFragment : Fragment(R.layout.fragment_result_view) {
    private val viewModel: ResultViewModel by viewModels()
    private val sharedViewModel: HomeConceptViewModel by activityViewModels()
    private val args: ResultViewFragmentArgs by navArgs()
    private lateinit var priceFilterBinding: PriceFilterPopupBinding
    private lateinit var regionFilterBinding: RegionFilterPopupBinding
    private lateinit var resultViewAdapter: ResultViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        priceFilterBinding = PriceFilterPopupBinding.inflate(layoutInflater)
        regionFilterBinding = RegionFilterPopupBinding.inflate(layoutInflater)
        val binding = FragmentResultViewBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener {
            sharedViewModel.onRequestBackPress()
        }
        viewModel.getInitializedStudio(args.conceptId)

        setupRvStudioList(binding)
        setupPriceFilterPopup(binding)
        setupRegionFilterPopup(binding)

        observePriceViewModel()
        observeResultViewModel()
    }

    private fun setupRvStudioList(binding: FragmentResultViewBinding) {
        resultViewAdapter = ResultViewAdapter()
        binding.rvStudioList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
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
                        viewModel.getInitializedStudio(args.conceptId)
                    }
                    btPriceLow.setOnClickListener {
                        viewModel.getStudioWithConceptOrderByLowerPrice(args.conceptId, Pricing.LOW)
                    }
                    btPriceMid.setOnClickListener {
                        viewModel.getStudioWithConceptOrderByLowerPrice(args.conceptId, Pricing.MEDIUM)
                    }
                    btPriceHigh.setOnClickListener {
                        viewModel.getStudioWithConceptOrderByLowerPrice(args.conceptId, Pricing.HIGH)
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
                    btGangnam.setOnCheckedChangeListener { _, _ ->
                        viewModel.onSelectedRegion(Region.Gangnam, args.conceptId)
                    }
                    btSeocho.setOnCheckedChangeListener { _, _ ->
                        viewModel.onSelectedRegion(Region.Seocho, args.conceptId)
                    }
                    btSongpa.setOnCheckedChangeListener { _, _ ->
                        viewModel.onSelectedRegion(Region.Songpa, args.conceptId)
                    }
                    btGangseo.setOnCheckedChangeListener { _, _ ->
                        viewModel.onSelectedRegion(Region.Gangseo, args.conceptId)
                    }
                    btMapo.setOnCheckedChangeListener { _, _ ->
                        viewModel.onSelectedRegion(Region.Mapo, args.conceptId)
                    }
                    btYeongdeungpo.setOnCheckedChangeListener { _, _ ->
                        viewModel.onSelectedRegion(Region.Yeongdeungpo, args.conceptId)
                    }
                    btGangbuk.setOnCheckedChangeListener { _, _ ->
                        viewModel.onSelectedRegion(Region.Gangbuk, args.conceptId)
                    }
                    btYongsan.setOnCheckedChangeListener { _, _ ->
                        viewModel.onSelectedRegion(Region.Yongsan, args.conceptId)
                    }
                    btSeongdong.setOnCheckedChangeListener { _, _ ->
                        viewModel.onSelectedRegion(Region.Seongdong, args.conceptId)
                    }
                }
            }
        }
    }

    private fun observePriceViewModel() {
        viewModel.selectedPrice.observe(viewLifecycleOwner) { selectedButton ->
            priceFilterBinding.apply {
                btPriceLow.isChecked = (selectedButton == Pricing.LOW)
                btPriceMid.isChecked = (selectedButton == Pricing.MEDIUM)
                btPriceHigh.isChecked = (selectedButton == Pricing.HIGH)
            }
        }
    }

    private fun observeRegionViewModel() {
        viewModel.selectedRegion.observe(viewLifecycleOwner) { filterState ->
            val selectedRegionIds = filterState.getSelectedRegionIds()
        }
    }

    private fun observeResultViewModel() {
        viewModel.result.observe(viewLifecycleOwner) { studioList ->
            resultViewAdapter.submitList(studioList)
        }
    }
}