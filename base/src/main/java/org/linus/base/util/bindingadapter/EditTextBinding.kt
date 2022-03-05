package org.linus.base.util.bindingadapter

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("onEditorEnterAction")
fun EditText.onEditorEnterAction(function: () -> Unit) {
    setOnEditorActionListener { _, actionId, event ->
        val imeAction = when(actionId) {
            EditorInfo.IME_ACTION_DONE,
            EditorInfo.IME_ACTION_SEND,
            EditorInfo.IME_ACTION_GO -> true
            else -> false
        }
        val keydownEvent = event?.keyCode == KeyEvent.KEYCODE_ENTER
                && event.action == KeyEvent.ACTION_DOWN
        if (imeAction or keydownEvent) {
            true.also {
                function.invoke()
            }
        } else false
    }
}
