package org.linus.base.presentation.ui.toast

import androidx.annotation.StringRes

interface Toaster {
    fun showToast(msg: String)
    fun showToast(@StringRes id: Int, vararg args: Any)
}