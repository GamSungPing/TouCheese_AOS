package com.example.presentation.main.view.fragment.reserve

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.databinding.FragmentReservationListBinding
import com.example.presentation.main.view.adapter.ReservationViewAdapter
import com.example.presentation.main.view.fragment.reserve.vm.ReservationDetailViewModel
import com.example.presentation.main.vm.model.MemberStatus
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kr.techit.lion.presentation.ext.repeatOnViewStarted

@AndroidEntryPoint
class ReservationListFragment : Fragment(R.layout.fragment_reservation_list) {
    private lateinit var reservationAdapter: ReservationViewAdapter
    private val viewModel: ReservationDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentReservationListBinding.bind(view)
        setRvReservationList(binding)
        initTabView(binding)
        observerReservations(binding)
        collectMemberId(binding)
    }

    private fun setRvReservationList(binding: FragmentReservationListBinding) {
        reservationAdapter = ReservationViewAdapter { position ->
            getSelectedReservationId(position)
        }

        binding.rvReservationList.apply {
            adapter = reservationAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }

    private fun getSelectedReservationId(position: Int) {
        viewModel.reservations.value?.let {
            val reservationId = it[position].id
            navigateReservationDetailView(reservationId)
        }
    }

    private fun navigateReservationDetailView(reservationId: Int) {
        val navController = NavHostFragment.findNavController(this)
        val action = ReservationListFragmentDirections
            .actionReservationFragmentToReservationDetailFragment(reservationId)
        navController.navigate(action)
    }

    private fun setTabListener(
        memberId: Long,
        binding: FragmentReservationListBinding
    ) {
        val tabLayout = binding.layoutReservationTab
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> getOngoingReservations(memberId)
                    1 -> getCompleteReservations(memberId)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun initTabView(binding: FragmentReservationListBinding) {
        val tabLayout = binding.layoutReservationTab
        tabLayout.addTab(tabLayout.newTab().setText("예약 일정"))
        tabLayout.addTab(tabLayout.newTab().setText("이전 내역"))
    }

    private fun getOngoingReservations(memberId: Long) {
        viewModel.getReservationsByMemberId(memberId)
    }

    private fun getCompleteReservations(memberId: Long) {
        viewModel.getCompletedReservationByMemberId(memberId)
    }

    private fun observerReservations(binding: FragmentReservationListBinding) {
        viewModel.reservations.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                reservationAdapter.submitList(it)
                showEmptyLayout(false, binding)
            } else {
                showEmptyLayout(true, binding)
            }
        }
    }

    private fun collectMemberId(binding: FragmentReservationListBinding) {
        repeatOnViewStarted {
            viewModel.memberId.collect {
                when (it) {
                    is MemberStatus.NonMember,
                    is MemberStatus.Loading -> Unit

                    is MemberStatus.Member -> {
                        showLoginLayout(false, binding)
                        showEmptyLayout(true, binding)
                        getOngoingReservations(it.memberId)
                        setTabListener(
                            memberId = it.memberId,
                            binding = binding
                        )
                    }
                }
            }
        }
    }

    private fun showEmptyLayout(isVisible: Boolean, binding: FragmentReservationListBinding) {
        with(binding) {
            layoutEmptyReservationList.isVisible = isVisible
        }
    }

    private fun showLoginLayout(isVisible: Boolean, binding: FragmentReservationListBinding) {
        with(binding) {
            layoutNonMember.isVisible = isVisible
        }
    }
}