package com.example.budgetapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.budgetapp.R
import com.example.budgetapp.databinding.FragmentTransactionsBinding
import com.example.budgetapp.ui.adapters.TransactionsRecyclerAdapter
import com.example.budgetapp.viewmodels.TransactionsViewModel
import kotlinx.android.synthetic.main.fragment_transactions.*
import org.koin.android.ext.android.inject


class TransactionsFragment : Fragment() {

    private lateinit var binding: FragmentTransactionsBinding

    private val viewModel: TransactionsViewModel by inject()

    private val transactionsAdapter = TransactionsRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_transactions, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        rvListOfTransactions.adapter = transactionsAdapter
        viewModel.refreshTransactions()

        viewModel.transactionsLiveData.observe(viewLifecycleOwner, Observer { it ->
            tvTotalSpend.text = it?.map {dbt ->
                dbt.totalPrice
            }?.sum().toString() + " kn"
        })
    }
}