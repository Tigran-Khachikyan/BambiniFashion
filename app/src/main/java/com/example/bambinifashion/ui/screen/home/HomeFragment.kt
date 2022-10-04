package com.example.bambinifashion.ui.screen.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bambinifashion.databinding.FragmentHomeBinding
import com.example.bambinifashion.ui.adapters.ContentAdapter
import com.example.bambinifashion.ui.utils.displayHtml
import com.example.bambinifashion.ui.utils.visibleOrGone

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var contentAdapter: ContentAdapter
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.init()
        viewModel.observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentHomeBinding.init() {
        with(recContent) {
            setHasFixedSize(true)
            contentAdapter = ContentAdapter()
            adapter = contentAdapter
        }
    }

    private fun HomeViewModel.observe() {
        promotion.observe(viewLifecycleOwner) { data ->
            data?.let {
                with(binding.tvPromotion) {
                    displayHtml(it.content)
                    setBackgroundColor(Color.parseColor(it.backgroundColor))
                    setTextColor(Color.parseColor(it.textColor))
                }
            }
        }
        content.observe(viewLifecycleOwner) { data ->
            contentAdapter.setListItems(data ?: listOf())
            binding.recContent.visibleOrGone(!data.isNullOrEmpty())
            binding.tvPageEmpty.visibleOrGone(data.isNullOrEmpty())
        }
    }

}