package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.databinding.FragmentReservationListBinding
import com.example.presentation.main.view.adapter.ReservationViewAdapter

class ReservationListFragment : Fragment(R.layout.fragment_reservation_list), ReservationViewAdapter.OnReservationClickListener {
   private lateinit var reservationAdapter: ReservationViewAdapter

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val binding = FragmentReservationListBinding.bind(view)
      setRvReservationList(binding)
   }

   private fun setRvReservationList(binding: FragmentReservationListBinding) {
      reservationAdapter = ReservationViewAdapter(this)

      binding.rvReservationList.apply {
         adapter = reservationAdapter
         layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

         val list = mutableListOf("스튜디오1", "스튜디오2", "스튜디오3")
         reservationAdapter.submitList(list)

      }
   }


   override fun onItemClick(position: Int) {
      super.onItemClick(position)
      setGoToDetailFragment(position)
   }

   private fun setGoToDetailFragment(reservationId: Int) {
      val navController = NavHostFragment.findNavController(this)
      val action = ReservationListFragmentDirections.actionReservationFragmentToReservationDetailFragment(reservationId)
      navController.navigate(action)
   }
}