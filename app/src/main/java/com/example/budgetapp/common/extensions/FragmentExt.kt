package com.example.budgetapp.common.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackbar(@StringRes stringRes: Int) {
    view?.let {
        Snackbar.make(it, stringRes, Snackbar.LENGTH_SHORT).show()
    }
}

fun Fragment.snackbar(text: String) {
    view?.let {
        Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show()
    }
}