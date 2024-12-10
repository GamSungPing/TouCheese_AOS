package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
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

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val binding = FragmentReservationListBinding.bind(view)
      setRvReservationList(binding)
      initTabView(binding, memberId)
      getOngoingReservations(memberId)
      setTabListener(binding)

      observerReservations()
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

   private fun getSelectedReservationId(id: Int) {
         viewModel.reservations.value?.let {
            val reservationId = it[id].id
            navigateReservationDetailView(reservationId)
      }
   }

   private fun navigateReservationDetailView(reservationId: Int) {
      val navController = NavHostFragment.findNavController(this)
      val action = ReservationListFragmentDirections.actionReservationFragmentToReservationDetailFragment(reservationId)
      navController.navigate(action)
   }

   private fun setTabListener(binding: FragmentReservationListBinding) {
      val tabLayout = binding.layoutReservationTab
      tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
         override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.position) {
               0 -> getOngoingReservations(memberId)
               1 -> getOngoingReservations(memberId)
            }
         }

         override fun onTabUnselected(tab: TabLayout.Tab?) {
         }

         override fun onTabReselected(tab: TabLayout.Tab?) {
         }
      })
   }

   private fun initTabView(binding: FragmentReservationListBinding, memberId: Int) {
      val tabLayout = binding.layoutReservationTab
      tabLayout.addTab(tabLayout.newTab().setText("예약내역"))
      tabLayout.addTab(tabLayout.newTab().setText("지난내역"))
   }

   private fun getOngoingReservations(memberId: Int) {
      viewModel.getReservationsByMemberId(memberId)
   }

   private fun getCompleteReservations(memberId: Int) {
      viewModel.getCompletedReservationByMemberId(memberId)
   }

   private fun observerReservations() {
      viewModel.reservations.observe(viewLifecycleOwner) {
         reservationAdapter.submitList(it)
      }
   }
}