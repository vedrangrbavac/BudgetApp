package com.example.budgetapp.util

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.common.extensions.format
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object BindingAdapters {

    @BindingAdapter("items")
    @JvmStatic
    fun <T> setData(recyclerView: RecyclerView, data: List<T>?) {
        if (recyclerView.layoutAnimation == null) {
            recyclerView.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(
                    recyclerView.context,
                    R.anim.layout_animation_fall_down
                )
        }
        with(recyclerView.adapter as? ListAdapter<T, *>) {
            this?.run {
                submitList(data)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @BindingAdapter("dateToText")
    @JvmStatic
    fun dateTimeToString(textView: TextView, localDate: LocalDate?) {
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        textView.text = localDate?.format(formatter)
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter("doubleToText")
    @JvmStatic
    fun doubleToStringWithTwoDecimals(textView: TextView, value: Double?) {
        textView.text = """${value?.format(2).toString()} kn"""
    }

    @BindingAdapter("items", "onItemSelected", requireAll = true)
    @JvmStatic
    fun <T> setSpinnerItems(
        spinner: AppCompatSpinner,
        items: List<T>?,
        onItemSelectedListener: AdapterView.OnItemSelectedListener?
    ) {
        items?.let {
            val spinnerAdapter =
                ArrayAdapter(spinner.context, android.R.layout.simple_spinner_item, it)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = spinnerAdapter
            onItemSelectedListener?.let { onItemSelectedListener ->
                spinner.onItemSelectedListener = onItemSelectedListener
            }
        }
    }

}