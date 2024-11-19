package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.databinding.FragmentResultViewBinding
import com.example.presentation.sample.ResultViewAdapter
import com.example.presentation.sample.Studio

class ResultViewFragment : Fragment() {
    private var _binding : FragmentResultViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var resultViewAdapter: ResultViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvStudioList()

        // 임시 데이터
        val sampleData = listOf(
            Studio(id = 1, name = "Studio 1", listOf(
                "image1",
                "image2",
                "image3",
                "image4",
                "image5",
            )),
            Studio(id = 2, name = "Studio 2", listOf(
                "image1",
                "image2",
                "image3",
                "image4",
                "image5",
            )),
            Studio(id = 3, name = "Studio 3", listOf(
                "image1",
                "image2",
                "image3",
                "image4",
                "image5",
            )),
            Studio(id = 4, name = "Studio 4", listOf(
                "image1",
                "image2",
                "image3",
                "image4",
                "image5",
            ))
        )

        resultViewAdapter.submitList(sampleData)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupRvStudioList() {
        resultViewAdapter = ResultViewAdapter()
        binding.rvStudioList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = resultViewAdapter
        }
    }
}