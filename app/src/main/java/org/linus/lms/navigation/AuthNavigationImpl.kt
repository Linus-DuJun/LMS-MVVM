package org.linus.lms.navigation

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.linus.lms.HomeActivity
import org.linus.lms.auth.ui.navigation.AuthNavigator
import javax.inject.Inject

class AuthNavigatorImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AuthNavigator {
    override fun toHomeActivity() {
        Intent().apply {
            setClass(context, HomeActivity::class.java)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }.also {
            context.startActivity(it)
        }
    }
}