package com.example.budgetapp.viewmodels

import android.graphics.Color
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.example.budgetapp.R
import com.example.budgetapp.common.base.BaseViewModel
import com.example.budgetapp.data.models.persistance.DBTransaction
import com.example.budgetapp.repositories.TransactionsRepository
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class StatsViewModel(private val repository: TransactionsRepository) : BaseViewModel(){

    private val transactionsLiveData: LiveData<List<DBTransaction>?> get() = repository.transactions

    var pieData: PieData? = null
    var pieDataSet: PieDataSet? = null
    private val pieEntries: MutableList<PieEntry> = arrayListOf()


    fun initializePieChart(pieChart: PieChart){
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




    fun getAllStatsData() {
        val transactions = transactionsLiveData.value
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

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnTotalStatsGraph ->{
                view.findNavController().navigate(R.id.action_graphFragment_to_allStatsFragment)
            }
            R.id.btnStatsByDate -> {
                view.findNavController().navigate(R.id.action_graphFragment_to_statsByDateFragment)
            }
            R.id.btnStatsBySpecificCategory -> {
                view.findNavController().navigate(R.id.action_graphFragment_to_statsByCategoryFragment)
            }

        }
    }
}