package com.example.budgetapp.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.budgetapp.R
import com.example.budgetapp.common.base.AbstractViewHolder
import com.example.budgetapp.common.base.BaseAdapter
import com.example.budgetapp.common.base.ItemClickListener
import com.example.budgetapp.data.database.DBTransaction
import com.example.budgetapp.databinding.LayoutTransactionsListItemBinding

class TransactionsRecyclerAdapter(itemClickListener: ((DBTransaction, Int) -> Unit)? = null) :
    BaseAdapter<DBTransaction, TransactionsViewHolder>(itemClickListener) {
    override val itemLayout: Int = R.layout.layout_transactions_list_item

    override fun createViewHolder(view: View): TransactionsViewHolder {
        val layoutInflater = LayoutInflater.from(view.context)
        val binding = LayoutTransactionsListItemBinding.inflate(layoutInflater, view as ViewGroup?, false)
        return TransactionsViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}

class TransactionsViewHolder(view: View) : AbstractViewHolder<DBTransaction>(view) {

    lateinit var binding: LayoutTransactionsListItemBinding

    constructor(binding: LayoutTransactionsListItemBinding) : this(binding.root) {
        this.binding = binding
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(model: DBTransaction, position: Int, listener: ItemClickListener<DBTransaction>) {

    }

}