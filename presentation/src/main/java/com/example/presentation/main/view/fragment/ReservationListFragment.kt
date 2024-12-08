package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.databinding.FragmentReservationListBinding
import com.example.presentation.main.view.adapter.ReservationViewAdapter
import com.google.android.material.tabs.TabLayout

class ReservationListFragment : Fragment(R.layout.fragment_reservation_list) {
   private lateinit var reservationAdapter: ReservationViewAdapter
   val list1 = mutableListOf("스튜디오1", "스튜디오2", "스튜디오3")
   val list2 = mutableListOf("스튜디오4", "스튜디오5", "스튜디오6")

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val binding = FragmentReservationListBinding.bind(view)
      setRvReservationList(binding)
      setTabListener(binding)
      initTabView(binding)
   }

   private fun setRvReservationList(binding: FragmentReservationListBinding) {
      reservationAdapter = ReservationViewAdapter{ position ->
         setGoToDetailFragment(position)
      }

      binding.rvReservationList.apply {
         adapter = reservationAdapter
         layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
      }
   }

   private fun setGoToDetailFragment(reservationId: Int) {
      val navController = NavHostFragment.findNavController(this)
      val action = ReservationListFragmentDirections.actionReservationFragmentToReservationDetailFragment(reservationId)
      navController.navigate(action)
   }

   private fun setTabListener(binding: FragmentReservationListBinding) {
      val tabLayout = binding.layoutReservationTab
      tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
         override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.position) {
               0 -> updateRecyclerViewData(list1)
               1 -> updateRecyclerViewData(list2)
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
      tabLayout.addTab(tabLayout.newTab().setText("예약내역"))
      tabLayout.addTab(tabLayout.newTab().setText("지난내역"))
      updateRecyclerViewData(list1)
   }

   private fun updateRecyclerViewData(data: List<String>) {
      reservationAdapter.submitList(data)
   }

}