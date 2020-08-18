package com.example.budgetapp.viewmodels

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.budgetapp.R
import com.example.budgetapp.common.base.BaseViewModel
import com.example.budgetapp.data.models.persistance.DBTransaction
import com.example.budgetapp.repositories.TransactionsRepository
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class StatsViewModel(private val repository: TransactionsRepository) : BaseViewModel() {

    private val transactionsLiveData: LiveData<List<DBTransaction>?> get() = repository.transactions

    var pieData: PieData? = null
    var pieDataSet: PieDataSet? = null
    private val pieEntries: MutableList<PieEntry> = arrayListOf()

    private var lineEntries: MutableList<Entry> = arrayListOf()

    val categoriesLiveData = MutableLiveData<List<String>>(
        listOf(
            "Social life", "Food", "Self development", "Transportation", "Health",
            "Beauty", "Education", "Household", "Other"
        )
    )

    val selectedCategory = MutableLiveData("")

    val onCategorySelected = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            //NOOP
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selectedCategory.value = categoriesLiveData.value?.get(position)
        }
    }


    fun initializePieChart(pieChart: PieChart) {
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

    fun initializeLineChart(lineChart: LineChart, selectedCategory: String) {
        val vl = LineDataSet(lineEntries, selectedCategory)
        vl.setDrawCircles(true)
        vl.lineWidth= 4f
        vl.circleRadius = 5f
        vl.color = Color.BLACK
        vl.valueTextSize = 18f
        lineChart.xAxis.labelRotationAngle = 0f
        lineChart.xAxis.isEnabled = false
        lineChart.data = LineData(vl)
        lineChart.axisRight.isEnabled = false
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)
        lineChart.setDrawGridBackground(false)
        lineChart.description.text = "Order by date"
        lineChart.setNoDataText("No data yet!")
        lineChart.animateX(1800, Easing.EaseInExpo)
    }

    fun getAmountByCategory(selectedCategory: String) {
        val transactions = transactionsLiveData.value
        var x = 1
        val lineEntriesTemp = ArrayList<Entry>()
        Log.d("Primljeno", selectedCategory)
        transactions?.sortedBy { it.date }?.forEach { dbt ->
            if (dbt.category.trim() == selectedCategory) {
                lineEntriesTemp.add(Entry(x.toFloat(), dbt.totalPrice.toFloat()))
                x++
            }
        }
        lineEntries = lineEntriesTemp.toMutableList()
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
            R.id.btnTotalStatsGraph -> {
                view.findNavController().navigate(R.id.action_graphFragment_to_allStatsFragment)
            }
            R.id.btnStatsByDate -> {
                view.findNavController().navigate(R.id.action_graphFragment_to_statsByDateFragment)
            }
            R.id.btnStatsBySpecificCategory -> {
                view.findNavController()
                    .navigate(R.id.action_graphFragment_to_statsByCategoryFragment)
            }

        }
    }
}