package com.example.bambinifashion.ui.screen.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bambinifashion.R
import com.example.bambinifashion.ui.screen.bag.BagViewModel

class MenuFragment : Fragment() {

    private val viewModel: BagViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
}