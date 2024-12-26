package com.example.presentation.main.view.fragment.like

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.databinding.FragmentLikeBinding
import com.example.presentation.main.customview.ConfirmDialog
import com.example.presentation.main.view.adapter.LikeViewAdapter
import com.example.presentation.main.view.fragment.like.vm.LikeViewModel
import com.example.presentation.main.vm.model.MemberStatus
import com.example.presentation.screen.studio.StudioActivity
import dagger.hilt.android.AndroidEntryPoint
import kr.techit.lion.presentation.ext.repeatOnViewStarted

@AndroidEntryPoint
class LikeFragment : Fragment(R.layout.fragment_like) {
    private lateinit var likeViewAdapter: LikeViewAdapter
    private val viewModel: LikeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLikeBinding.bind(view)
        setRvLike(binding)
        initView(binding)
    }

    private fun setRvLike(binding: FragmentLikeBinding) {
        likeViewAdapter = LikeViewAdapter(
            onClickStudio = { studioId, profileURL ->
                val intent = Intent(requireContext(), StudioActivity::class.java).apply {
                    putExtra("studioId", studioId)
                    putExtra("profileURL", profileURL)
                }
                startActivity(intent)
            },
            onClickLike = { studioId ->
                deleteLike(studioId)
                getLikeList()
            }
        )

        binding.rvLike.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = null
            adapter = likeViewAdapter
        }
    }

    private fun getLikeList() {
        viewModel.getLikesByMemberId()
    }

    private fun deleteLike(studioId: Int) {
        viewModel.deleteLike(studioId)
    }

    private fun observeLikeList() {
        viewModel.likes.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                likeViewAdapter.submitList(it)
            }
        }
    }

    private fun showEmptyLayout(binding: FragmentLikeBinding) {
        binding.layoutEmptyLike.isVisible = false
    }

    private fun showLoginLayout(isVisible: Boolean, binding: FragmentLikeBinding) {
        binding.layoutNonMember.isVisible = isVisible
    }

    private fun initView(binding: FragmentLikeBinding) {
        repeatOnViewStarted {
            viewModel.memberId.collect {
                when (it) {
                    MemberStatus.Loading -> return@collect
                    is MemberStatus.Member -> {
                        getLikeList()
                        observeLikeList()
                        showEmptyLayout(binding)
                        showLoginLayout(false, binding)
                    }
                    MemberStatus.NonMember -> {
                        setAlertDialog()
                        showLoginLayout(true, binding)
                    }
                }
            }
        }
    }

    private fun setAlertDialog() {
        val dialog = ConfirmDialog(
            text = "로그인이 필요한 서비스입니다\n로그인하시겠습니까?",
        ) {
            // 로그인 화면으로 이동
        }
        dialog.show(childFragmentManager, "ConfirmDialog")
    }
}