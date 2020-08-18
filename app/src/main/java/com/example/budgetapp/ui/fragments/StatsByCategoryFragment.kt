package com.example.budgetapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.budgetapp.R
import com.example.budgetapp.databinding.FragmentStatsByCategoryBinding
import com.example.budgetapp.viewmodels.StatsViewModel
import kotlinx.android.synthetic.main.fragment_stats_by_category.*
import org.koin.android.ext.android.inject

class StatsByCategoryFragment : Fragment() {

    lateinit var binding: FragmentStatsByCategoryBinding

    private val viewModel: StatsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_stats_by_category, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.selectedCategory.observe(viewLifecycleOwner, Observer {
            Log.d("Observer", it)
            viewModel.getAmountByCategory(it)
            viewModel.initializeLineChart(lcStatsByCategory, it)
        })


    }
}