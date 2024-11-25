package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region
import com.example.presentation.R
import com.example.presentation.databinding.BottomSheetFilterPriceBinding
import com.example.presentation.databinding.BottomSheetFilterRegionBinding
import com.example.presentation.databinding.FragmentResultViewBinding
import com.example.presentation.main.view.adapter.ResultViewAdapter
import com.example.presentation.main.vm.HomeConceptViewModel
import com.example.presentation.main.vm.ResultViewModel
import com.example.presentation.main.vm.model.FilterState
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultViewFragment : Fragment(R.layout.fragment_result_view) {
    private val viewModel: ResultViewModel by viewModels()
    private val sharedViewModel: HomeConceptViewModel by activityViewModels()
    private val args: ResultViewFragmentArgs by navArgs()
    private lateinit var checkBoxes: List<CheckBox>
    private lateinit var resultViewAdapter: ResultViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentResultViewBinding.bind(view)

        with(binding){
            toolbar.setNavigationOnClickListener {
                sharedViewModel.onRequestBackPress()
            }
        }

        viewModel.getInitializedStudio(args.conceptId)
        setupRvStudioList(binding)
        observeResultViewModel()
        observeFilterState()
    }

    private fun setupRvStudioList(binding: FragmentResultViewBinding) {
        resultViewAdapter = ResultViewAdapter()

        binding.rvStudioList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = null
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = resultViewAdapter
        }

        binding.btFilterPrice.setOnClickListener {
            showPriceFilterBottomSheet()

        }

        binding.btFilterRegion.setOnClickListener {
            showRegionFilterBottomSheet()
        }

        binding.btReset.apply {
            setOnClickListener {
                viewModel.clearAllFilters()
                viewModel.getInitializedStudio(args.conceptId)
            }
        }

        binding.btFilterRating.apply {
            setOnClickListener {
                viewModel.hasRatingFilter = true
                viewModel.checkFilterOption(args.conceptId)
            }
        }
    }

    private fun observeResultViewModel() {
        viewModel.result.observe(viewLifecycleOwner) { studioList ->
            if (studioList.isNotEmpty()) {
                resultViewAdapter.submitList(studioList)
            } else {
                resultViewAdapter.submitList(null)
            }
        }
    }

    private fun observeFilterState() {
        viewModel.filterState.observe(viewLifecycleOwner, Observer { filterState ->

        })
    }

    private fun showPriceFilterBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val priceBottomBinding = BottomSheetFilterPriceBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(priceBottomBinding.root)
        bottomSheetDialog.show()

        with(priceBottomBinding) {
            btDone.setOnClickListener {
                when (btRadioGroup.checkedRadioButtonId) {
                    btPriceAll.id -> {
                        viewModel.clear()
                    }
                    btPriceLow.id -> {
                        viewModel.updatePrice(Pricing.LOW)
                    }
                    btPriceMid.id -> {
                        viewModel.updatePrice(Pricing.MEDIUM)
                    }
                    btPriceHigh.id -> {
                        viewModel.updatePrice(Pricing.HIGH)
                    }
                }
                viewModel.checkFilterOption(args.conceptId)
                bottomSheetDialog.dismiss()
            }
        }
    }


    private fun showRegionFilterBottomSheet() {
        val bottomRegionDialog = BottomSheetDialog(requireContext())
        val regionBinding = BottomSheetFilterRegionBinding.inflate(layoutInflater)

        bottomRegionDialog.setContentView(regionBinding.root)
        bottomRegionDialog.show()

        checkBoxes = listOf(
            regionBinding.checkboxGangnam,
            regionBinding.checkboxSeocho,
            regionBinding.checkboxSongpa,
            regionBinding.checkboxGangseo,
            regionBinding.checkboxMapo,
            regionBinding.checkboxYeongdeunpo,
            regionBinding.checkboxGangbuk,
            regionBinding.checkboxYongsan,
            regionBinding.checkboxSeongdong
        )


        with(regionBinding) {
            checkboxParent.setOnCheckedChangeListener { _, isChecked ->
                checkBoxes.forEach { it.isChecked = isChecked }
            }

            checkBoxes.forEach { checkBox ->
                checkBox.setOnCheckedChangeListener { _, _ ->
                    checkboxParent.isChecked = checkBoxes.all { it.isChecked }

                }
            }

            checkboxGangnam.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateRegions(Region.Gangnam, isChecked )
            }
            checkboxSeocho.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateRegions(Region.Seocho, isChecked )
            }
            checkboxSongpa.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateRegions(Region.Songpa, isChecked )
            }
            checkboxGangseo.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateRegions(Region.Gangseo, isChecked )
            }
            checkboxMapo.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateRegions(Region.Mapo, isChecked )
            }
            checkboxYeongdeunpo.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateRegions(Region.Yeongdeungpo, isChecked )
            }
            checkboxGangbuk.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateRegions(Region.Gangbuk, isChecked )
            }
            checkboxYongsan.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateRegions(Region.Yongsan, isChecked )
            }
            checkboxSeongdong.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateRegions(Region.Seongdong, isChecked )
            }

            btDone.setOnClickListener {
                bottomRegionDialog.dismiss()
            }

            bottomRegionDialog.setOnDismissListener {
                viewModel.checkFilterOption(args.conceptId)
            }
        }
    }

    private fun clearRegion() {
        viewModel.filterState.value?.clearRegions()
    }
}