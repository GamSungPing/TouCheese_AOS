package com.example.presentation.main.view.fragment.concept

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region
import com.example.presentation.R
import com.example.presentation.databinding.BottomSheetFilterPriceBinding
import com.example.presentation.databinding.BottomSheetFilterRegionBinding
import com.example.presentation.databinding.FragmentResultViewBinding
import com.example.presentation.login.LoginActivity
import com.example.presentation.main.customview.ConfirmDialog
import com.example.presentation.main.view.MainActivity
import com.example.presentation.main.view.adapter.ResultViewAdapter
import com.example.presentation.screen.concept.vm.HomeConceptViewModel
import com.example.presentation.main.view.fragment.like.vm.LikeViewModel
import com.example.presentation.main.view.fragment.concept.vm.ResultViewModel
import com.example.presentation.screen.studio.StudioActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kr.techit.lion.presentation.ext.repeatOnViewStarted

@AndroidEntryPoint
class ResultViewFragment : Fragment(R.layout.fragment_result_view) {
    private val viewModel: ResultViewModel by viewModels()
    private val likeViewModel: LikeViewModel by viewModels()
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
        observeStudiosForNonMember()
        observeStudiosForMember()
        observeFilterState(binding)
        observeEmpty(binding)
        collectMemberId()
    }

    private fun setupRvStudioList(binding: FragmentResultViewBinding) {
        resultViewAdapter = ResultViewAdapter(
            onClickStudio = { studioId, profileURL ->
                val intent = Intent(requireContext(), StudioActivity::class.java).apply {
                    putExtra("studioId", studioId)
                    putExtra("profileURL", profileURL)
                }
                startActivity(intent)
            },
            onClickLike = {
                if (viewModel.memberId.value != 0L) {
                    likeViewModel.addLike(studioId = it, viewModel.memberId.value)
                } else{
                    setAlertDialog()
                }
            }
        )

        binding.rvStudioList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = null
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
                val currentState = viewModel.filterState.value?.hasRatingFilter
                if (currentState != null) {
                    viewModel.updateRating(!currentState)
                    viewModel.checkFilterOption(args.conceptId)
                }
            }
        }
    }

    private fun observeStudiosForNonMember() {
        viewModel.result.observe(viewLifecycleOwner) { studioList ->
            if (studioList.isNotEmpty()) {
                resultViewAdapter.submitList(studioList)
            } else {
                resultViewAdapter.submitList(null)
                viewModel.updateEmpty(true)
            }
        }
    }

    private fun observeStudiosForMember()  {
        viewModel.studioWithConceptAndLiked.observe(viewLifecycleOwner) {
            resultViewAdapter.submitList(it)
        }
    }

    private fun observeFilterState(binding: FragmentResultViewBinding) {
        viewModel.filterState.observe(viewLifecycleOwner) { filterState ->
            with(binding) {
                btFilterRating.isSelected = filterState.hasRatingFilter
                btFilterRegion.isSelected = filterState.hasSelectedRegion()
                btFilterPrice.isSelected = filterState.hasPriceFilter
            }
        }
    }

    private fun observeEmpty(binding: FragmentResultViewBinding) {
        viewModel.empty.observe(viewLifecycleOwner) {
            showEmptyView(binding, it)
        }
    }

    private fun collectMemberId(){
        repeatOnViewStarted {
            viewModel.memberId.collect {
                if (it != 0L) {
                    getLikedStudios(args.conceptId, it)
                }else{
                    getLikedStudios(args.conceptId, null)
                }
            }
        }
    }

    private fun getLikedStudios(conceptId: Int, memberId: Long?) {
        viewModel.getLikedStudios(conceptId, memberId)
    }

    private fun showPriceFilterBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val priceBottomBinding = BottomSheetFilterPriceBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(priceBottomBinding.root)
        bottomSheetDialog.show()

        with(priceBottomBinding) {
            val state = viewModel.filterState.value

            if(state?.hasPriceFilter == true) {
                when(state.pricing) {
                 Pricing.LOW -> { btRadioGroup.check(btPriceLow.id) }
                 Pricing.MEDIUM -> { btRadioGroup.check(btPriceMid.id) }
                 Pricing.HIGH -> { btRadioGroup.check(btPriceHigh.id) }
                }
            }

            btDone.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.setOnDismissListener {
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
                viewModel.clearRegionFilters()
            }
        }
    }

    private fun showEmptyView(binding: FragmentResultViewBinding, isBoolean: Boolean) {
        binding.rvStudioList.isVisible = isBoolean
        binding.layoutEmptyResult.isVisible = !isBoolean
    }

    private fun setAlertDialog() {
        val dialog = ConfirmDialog(
            text = "로그인이 필요한 서비스입니다\n로그인하시겠습니까?",
        ) {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
        dialog.show(childFragmentManager, "ConfirmDialog")
    }
}