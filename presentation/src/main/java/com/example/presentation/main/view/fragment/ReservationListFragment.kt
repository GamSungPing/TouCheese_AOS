package com.example.presentation.main.view.fragment

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
import com.example.presentation.main.vm.ReservationDetailViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationListFragment : Fragment(R.layout.fragment_reservation_list) {
   private lateinit var reservationAdapter: ReservationViewAdapter
   private val viewModel: ReservationDetailViewModel by viewModels()
   private val memberId = 2
   private val hasLogin = false

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val binding = FragmentReservationListBinding.bind(view)
      setRvReservationList(binding)
      initTabView(binding)
      setTabListener(hasLogin, binding)
      observerReservations(binding)
   }

   private fun setRvReservationList(binding: FragmentReservationListBinding) {
      reservationAdapter = ReservationViewAdapter{ position ->
         getSelectedReservationId(position)
      }

      binding.rvReservationList.apply {
         adapter = reservationAdapter
         layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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
      val action = ReservationListFragmentDirections.actionReservationFragmentToReservationDetailFragment(reservationId, memberId)
      navController.navigate(action)
   }

   private fun setTabListener(hasLogin: Boolean, binding: FragmentReservationListBinding) {
      val tabLayout = binding.layoutReservationTab
      tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
         override fun onTabSelected(tab: TabLayout.Tab?) {
            if(hasLogin) {
               showLoginLayout(false, binding)

               when (tab?.position) {
                  0 -> getOngoingReservations(memberId)
                  1 -> getCompleteReservations(memberId)
               }
            } else {
               showLoginLayout(true, binding)
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

   private fun getOngoingReservations(memberId: Int) {
      viewModel.getReservationsByMemberId(memberId)
   }

   private fun getCompleteReservations(memberId: Int) {
      viewModel.getCompletedReservationByMemberId(memberId)
   }

   private fun observerReservations(binding: FragmentReservationListBinding) {
      viewModel.reservations.observe(viewLifecycleOwner) {
         if(it.isNotEmpty()) {
            reservationAdapter.submitList(it)
            showEmptyLayout(false, binding)
         } else {
            showEmptyLayout(true, binding)
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