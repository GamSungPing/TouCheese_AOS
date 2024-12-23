package com.example.presentation.main.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.databinding.FragmentLikeBinding
import com.example.presentation.main.view.adapter.LikeViewAdapter
import com.example.presentation.studio.StudioActivity


class LikeFragment : Fragment(R.layout.fragment_like) {
    private lateinit var likeViewAdapter: LikeViewAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLikeBinding.bind(view)
        setRvLike(binding)
    }

    private fun setRvLike(binding: FragmentLikeBinding) {
        likeViewAdapter = LikeViewAdapter{ studioId, profileURL ->
            val intent = Intent(requireContext(), StudioActivity::class.java).apply {
                putExtra("studioId", studioId)
                putExtra("profileURL", profileURL)
            }
            startActivity(intent)
        }

        binding.rvLike.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = null
            adapter = likeViewAdapter
        }
    }
}