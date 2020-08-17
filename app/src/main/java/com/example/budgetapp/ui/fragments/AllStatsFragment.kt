package com.example.budgetapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.budgetapp.R
import com.example.budgetapp.databinding.FragmentAllStatsBinding
import com.example.budgetapp.viewmodels.StatsViewModel
import kotlinx.android.synthetic.main.fragment_all_stats.*
import org.koin.android.ext.android.inject


class AllStatsFragment : Fragment() {

    private lateinit var binding: FragmentAllStatsBinding
    private val viewModel: StatsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_stats, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getAllStatsData()
        viewModel.initializePieChart(pcAllStats)

    }

}