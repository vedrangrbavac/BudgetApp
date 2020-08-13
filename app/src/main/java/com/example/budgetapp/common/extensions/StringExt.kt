package com.example.budgetapp.common.extensions

fun Double.format(digits: Int) = "%.${digits}f".format(this)