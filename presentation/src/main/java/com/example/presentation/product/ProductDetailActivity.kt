package com.example.presentation.product

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.domain.model.ProductDetail
import com.example.presentation.databinding.ActivityProductDetailBinding
import com.example.presentation.product.vm.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {
    private val viewModel: ProductDetailViewModel by viewModels()
    private val optionCheckBoxes = mutableListOf<CheckBox>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getIntExtra("productId", 0)
        val description = intent.getStringExtra("description") ?: ""
        val path = intent.getStringExtra("path") ?: ""

        binding.tvProductDescription.text = description
        binding.btnBack.setOnClickListener { finish() }

        viewModel.getProductDetailByProductId(productId)
        bindImage(path, binding)
        collectReservationState(binding)
        observeOrderButtonEnable(binding)
        observerAddGuestCount(binding)
        observerSumPriceAddGuest(binding)

        showCalendarBottomSheet(binding)

        setupAddGuestButtonOnClickListener(binding)
        setupParentCheckBoxListener(binding)
    }

    private fun showCalendarBottomSheet(bind: ActivityProductDetailBinding) {
        bind.btnReservation.setOnClickListener {
            val bottomSheet = CalendarFragment()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }

    private fun collectReservationState(
        binding: ActivityProductDetailBinding,
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    val product = state.product
                    setProductDetail(binding, product, state.productPrice)
                    binding.btnReservation.text = state.reservation.dateTime
                }
            }
        }
    }

    private fun observeOrderButtonEnable(bind: ActivityProductDetailBinding) {
        viewModel.isOrderEnabled.observe(this) {
            bind.btOrder.isEnabled = it
        }
    }

    private fun setProductDetail(
        binding: ActivityProductDetailBinding,
        product: ProductDetail,
        price: String,
    ) {
        with(binding) {
            tvProductName.text = product.productName
            tvPriceSum.text = price

            binding.tvBaseGuestCount.text = product.basePeopleCnt.toString()
            if (product.isGroup) setupGroupOptionVisible(binding)
            product.productOptions?.let {
                if (it.isEmpty()) makeAddOptionCheckBox(it, binding)
            } ?: invisibleAddOptionCheckBox(binding)
        }
    }

    private fun observerAddGuestCount(bind: ActivityProductDetailBinding) {
        viewModel.addGuestCount.observe(this) {
            bind.tvCurrentAddGuestCount.text = it.toString()
        }
    }

    private fun observerSumPriceAddGuest(bind: ActivityProductDetailBinding) {
        viewModel.sumPriceAddGuest.observe(this) {
            bind.tvAddGuestTitle.text = "추가인원 (${it}원)"
        }
    }

    private fun invisibleAddOptionCheckBox(bind: ActivityProductDetailBinding) {
        bind.layoutAddOptionCheckbox.isVisible = false
    }

    private fun makeAddOptionCheckBox(
        options: List<String>,
        bind: ActivityProductDetailBinding
    ) {
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

            val checkBox = CheckBox(this).apply {
                text = "${optionName} (${optionPrice}원)"
                id = View.generateViewId()
            }

            checkBox.layoutParams = params
            bind.layoutAddOptionCheckbox.addView(checkBox)
            optionCheckBoxes.add(checkBox)
        }
    }

    fun updateButtonEnable(isEnabled: Boolean, binding: ActivityProductDetailBinding) {
        viewModel.setButtonEnabled(isEnabled)
    }

    private fun bindImage(imageUrl: String, binding: ActivityProductDetailBinding) {
        Glide.with(binding.root)
            .load(imageUrl)
            .into(binding.ivProductImage)
    }

    private fun setupParentCheckBoxListener(binding: ActivityProductDetailBinding) {
        binding.ckAddOptionNot.setOnCheckedChangeListener { _, isChecked ->
            optionCheckBoxes.forEach { checkBox ->
                checkBox.isChecked = false
                checkBox.isEnabled = !isChecked
            }
        }
    }

    private fun setupGroupOptionVisible(
        binding: ActivityProductDetailBinding
    ) {
        binding.layoutGuest.isVisible = true
        binding.layoutAddGuest.isVisible = true
    }

    private fun setupAddGuestButtonOnClickListener(binding: ActivityProductDetailBinding) {
        binding.btPlusGuestNumber.setOnClickListener {
            viewModel.increaseAddGuestCount()
            viewModel.setSumPriceAddGuest()
        }

        binding.btMinusGuestNumber.setOnClickListener {
            viewModel.decreaseAddGuestCount()
            viewModel.setSumPriceAddGuest()
        }
    }
}