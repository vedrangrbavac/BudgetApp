package com.example.budgetapp.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.budgetapp.R
import com.example.budgetapp.databinding.FragmentGraphBinding
import com.example.budgetapp.viewmodels.TransactionsViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_graph.*
import org.koin.android.ext.android.inject


class GraphFragment : Fragment() {

    private lateinit var binding: FragmentGraphBinding

    var pieData: PieData? = null
    var pieDataSet: PieDataSet? = null
    private val pieEntries: MutableList<PieEntry> = arrayListOf()

    private val viewModel: TransactionsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_graph, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getEntries()
        getRealEntries()
        pieDataSet = PieDataSet(pieEntries, "")
        pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieDataSet!!.setColors(*ColorTemplate.JOYFUL_COLORS)
        pieDataSet!!.sliceSpace = 2f
        pieDataSet!!.valueTextColor = Color.WHITE
        pieDataSet!!.valueTextSize = 10f
        pieDataSet!!.sliceSpace = 5f
        // pieChart.setUsePercentValues(true)
        pieChart.animateXY(1500, 1500)
    }


    private fun getEntries() {
        pieEntries.add(PieEntry(2f, "Food"))
        pieEntries.add(PieEntry(4f, "Social life"))
        pieEntries.add(PieEntry(6f, "Self development"))
        pieEntries.add(PieEntry(8f, "Transportation"))
        pieEntries.add(PieEntry(7f, "Education"))
        pieEntries.add(PieEntry(3f, "Other"))
    }

    private fun getRealEntries() {
        val transactions = viewModel.transactionsLiveData.value
        var pieEntriesTemp = pieEntries.toMutableList()

        transactions?.forEach { dbt ->
            if (pieEntriesTemp.isEmpty()) {
                pieEntries.add(PieEntry(dbt.totalPrice.toFloat(), dbt.category.trim()))
            } else {
                var categoryAlreadyExists = false
                pieEntriesTemp.forEach { peTemp ->
                    if (peTemp.label.trim() == dbt.category.trim())
                        categoryAlreadyExists = true
                }
                if (!categoryAlreadyExists) {
                    pieEntries.add(PieEntry(dbt.totalPrice.toFloat(), dbt.category.trim()))
                } else {
                    var newValue = dbt.totalPrice.toFloat()
                    pieEntriesTemp.forEach { pe ->
                        if (pe.label.trim() == dbt.category.trim()) {
                            newValue += pe.value
                            pieEntries.remove(pe)
                            pieEntries.add(PieEntry(newValue, dbt.category.trim()))
                        }
                    }
                }
            }
            pieEntriesTemp = pieEntries.toMutableList()
        }
    }
}