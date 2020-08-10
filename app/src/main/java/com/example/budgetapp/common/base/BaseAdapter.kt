package com.example.budgetapp.common.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

typealias ItemClickListener<T> = ((T, Int) -> Unit)?

fun <T> createDefaultDiffCallback() = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) ?: false
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) ?: false
    }
}

abstract class BaseAdapter<M, VH : AbstractViewHolder<M>>(
    private val listener: ItemClickListener<M>,
    diffCallback: DiffUtil.ItemCallback<M> = createDefaultDiffCallback()
) : ListAdapter<M, VH>(diffCallback) {

    protected abstract val itemLayout: Int

    protected abstract fun createViewHolder(view: View): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position), position, listener)
    }

}

