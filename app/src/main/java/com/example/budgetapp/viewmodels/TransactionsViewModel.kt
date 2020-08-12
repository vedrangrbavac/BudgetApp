package com.example.budgetapp.viewmodels

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.budgetapp.R
import com.example.budgetapp.common.base.BaseViewModel
import com.example.budgetapp.data.models.persistance.DBTransaction
import com.example.budgetapp.repositories.TransactionsRepository
import kotlinx.android.synthetic.main.fragment_add_transaction.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class TransactionsViewModel(private val repository: TransactionsRepository) : BaseViewModel() {

    val transactionsLiveData: LiveData<List<DBTransaction>?> get() = repository.transactions

    val categoriesLiveData = MutableLiveData<List<String>>(
        listOf(
            "Social life", "Food", "Self development", "Transportation", "Health",
            "Beauty", "Education", "Household", "Other"
        )
    )

    val amountLiveData = MutableLiveData<String?>()
    val contentsLiveData = MutableLiveData<String?>()
    val dateLiveData = MutableLiveData<String?>()

    val selectedCategory = MutableLiveData("")

    val onCategorySelected = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            //NOOP
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selectedCategory.value = categoriesLiveData.value?.get(position)
        }
    }

    fun refreshTransactions() {
        suspendCall {
            repository.refreshTransactions()
        }
    }

    val filteredData = MediatorLiveData<List<DBTransaction>>().apply {
        listOf(
            transactionsLiveData
        ).forEach { it ->
            addSource(it) {
                val filteredValue = transactionsLiveData.value
                value = filteredValue
            }
        }
    }

    private fun saveTransaction(dbTransaction: DBTransaction) {
        suspendCall {
            repository.saveTransaction(dbTransaction)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClick(view: View) {
        when (view.id) {
            R.id.fabAddTransaction -> {
                view.findNavController()
                    .navigate(R.id.action_transactionsFragment_to_addTransactionFragment)
            }
            R.id.btnSave -> {
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.GERMAN)
                val ld = LocalDate.parse(dateLiveData.value.toString(), formatter)
                Log.d("Date", ld.toString())
                //                Log.d("Data", contentsLiveData.value.toString() + LocalDate.now() + "/n" + selectedCategory.toString() + "/n" + amountLiveData.value!!.toDouble())
                saveTransaction(DBTransaction(contentsLiveData.value.toString(), ld, selectedCategory.value.toString(), amountLiveData.value!!.toDouble()))
            }

        }
    }
}


