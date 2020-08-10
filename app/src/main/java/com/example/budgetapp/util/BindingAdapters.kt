package com.example.budgetapp.util

import android.os.Build
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import java.time.LocalDateTime
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
                Log.d("data", data.toString())
                submitList(data)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @BindingAdapter("dateToText")
    @JvmStatic
    fun dateTimeToString(textView: TextView, localDateTime: LocalDateTime?) {
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        textView.text = localDateTime?.format(formatter)
    }

}