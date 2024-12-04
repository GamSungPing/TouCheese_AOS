package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.databinding.FragmentProductDetailViewBinding
import com.example.presentation.main.vm.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailViewFragment : Fragment(R.layout.fragment_product_detail_view) {

    private var _binding: FragmentProductDetailViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductDetailViewModel by viewModels()

    private val optionCheckBoxes = mutableListOf<CheckBox>()
    private val arg = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind = FragmentProductDetailViewBinding.bind(view)

        viewModel.getAllProductWithStudioId(arg)
        viewModel.getProductDetailByProductId(arg)

        observeReservationState()
        observeOrderButtonEnable(bind)
        observerProduct(bind)
        observerProductDetail(bind)
        observerAddGuestCount(bind)
        observerSumPriceAddGuest(bind)

        showCalendarBottomSheet(bind)
        showChangeCalendarBottomSheet(bind)

        setupAddGuestButtonOnClickListener(bind)
        setupParentCheckBoxListener(bind)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showCalendarBottomSheet(bind : FragmentProductDetailViewBinding) {
        bind.btSelectReservationDay.setOnClickListener {
            val bottomSheet = CalendarFragment()
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }
    }

    private fun showChangeCalendarBottomSheet(bind : FragmentProductDetailViewBinding) {
        bind.btChangeReservationDay.setOnClickListener {
            val bottomSheet = CalendarFragment()
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }
    }

    private fun observeReservationState() {
        viewModel.reservationState.observe(viewLifecycleOwner) { reservationState ->
        }
    }

    private fun observeOrderButtonEnable(bind : FragmentProductDetailViewBinding) {
        viewModel.isOrderEnabled.observe(viewLifecycleOwner) {
            bind.btOrder.isEnabled = it
        }
    }

    private fun observerProduct(bind : FragmentProductDetailViewBinding) {
        viewModel.product.observe(viewLifecycleOwner) {
            it?.let {
                val product = it[arg - 1]

                val url = product.imageString
                bindImage(url, bind)

                val name = product.name
                bind.tvProductName.text = name

                val description = product.description
                bind.tvProductDescription.text = description

                val isGroup = product.isGroup
                setupGroupOptionVisible(isGroup, bind)

                val price = product.price
                bind.tvPriceSum.text = price.toString()
            }
        }
    }

    private fun observerProductDetail(bind : FragmentProductDetailViewBinding) {
        viewModel.productDetail.observe(viewLifecycleOwner) { productDetail ->
            productDetail?.let {
                bind.tvBaseGuestCount.text = it.baseGuestCount.toString()

                it.productOptions?.run {
                    makeAddOptionCheckBox(this, bind)
                } ?: invisibleAddOptionCheckBox(bind)
            }
        }
    }

    private fun observerAddGuestCount(bind : FragmentProductDetailViewBinding) {
        viewModel.addGuestCount.observe(viewLifecycleOwner) {
            bind.tvCurrentAddGuestCount.text = it.toString()
        }
    }

    private fun observerSumPriceAddGuest(bind : FragmentProductDetailViewBinding) {
        viewModel.sumPriceAddGuest.observe(viewLifecycleOwner) {
            bind.tvAddGuestTitle.text = "추가인원 (${it}원)"
        }
    }

    private fun invisibleAddOptionCheckBox(bind : FragmentProductDetailViewBinding) {
        bind.layoutAddOptionCheckbox.isVisible = false
    }

    private fun makeAddOptionCheckBox(options: List<String>, bind : FragmentProductDetailViewBinding) {
        bind.layoutAddOptionCheckbox.removeAllViews()
        optionCheckBoxes.clear()

        for (option in options) {
            val parts = option.split(":")
            val optionName = parts[0]
            val optionPrice = parts[1]

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 10)

            val checkBox = CheckBox(requireContext()).apply {
                text = "${optionName} (${optionPrice}원)"
                id = View.generateViewId()
            }

            checkBox.layoutParams = params
            bind.layoutAddOptionCheckbox.addView(checkBox)
            optionCheckBoxes.add(checkBox)
        }
    }

    fun updateText(text: String) {
        binding.tvSelectedDate.text = text
    }

    fun updateButtonEnable(isEnabled: Boolean) {
        viewModel.setButtonEnabled(isEnabled)
        toggleReservationButtonVisibility(isEnabled)
    }

    private fun toggleReservationButtonVisibility(boolean: Boolean) {
        binding.btSelectReservationDay.isVisible = !boolean
        binding.tvSelectedDate.isVisible = boolean
        binding.btChangeReservationDay.isVisible = boolean
    }

    private fun bindImage(imageUrl: String, bind : FragmentProductDetailViewBinding) {
        Glide.with(bind.root)
            .load(imageUrl)
            .into(bind.ivProductImage)
    }

    private fun setupParentCheckBoxListener(bind : FragmentProductDetailViewBinding) {
       bind.ckAddOptionNot.setOnCheckedChangeListener { _, isChecked ->
           optionCheckBoxes.forEach { checkBox ->
               checkBox.isChecked = false
               checkBox.isEnabled = !isChecked
           }
       }
    }

    private fun setupGroupOptionVisible(boolean: Boolean, bind : FragmentProductDetailViewBinding) {
        bind.layoutGuest.isVisible = boolean
        bind.layoutAddGuest.isVisible = boolean
    }

    private fun setupAddGuestButtonOnClickListener(bind : FragmentProductDetailViewBinding) {
        bind.btPlusGuestNumber.setOnClickListener {
            viewModel.increaseAddGuestCount()
            viewModel.setSumPriceAddGuest()
        }

        bind.btMinusGuestNumber.setOnClickListener {
            viewModel.decreaseAddGuestCount()
            viewModel.setSumPriceAddGuest()
        }
    }
}