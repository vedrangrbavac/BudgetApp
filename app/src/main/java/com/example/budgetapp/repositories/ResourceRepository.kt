package com.example.budgetapp.repositories

import android.content.Context

class ResourceRepository (val context: Context){
    @SuppressWarnings("SpreadOperator")
    fun getString(resId: Int, vararg formatValues: Any) = context.resources.getString(resId, *formatValues)

    fun getManager() = context.getSystemService(Context.DOWNLOAD_SERVICE)
}