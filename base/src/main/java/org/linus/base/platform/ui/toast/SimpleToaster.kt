package org.linus.base.platform.ui.toast

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SimpleToaster @Inject constructor(@ApplicationContext private val context: Context): Toaster {
    override fun showToast(msg: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    }

    override fun showToast(id: Int, vararg args: Any) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, context.getString(id, *args), Toast.LENGTH_LONG).show()
        }
    }

}