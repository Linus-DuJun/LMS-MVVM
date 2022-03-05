package org.linus.lms.auth.presentation.ui

import dagger.hilt.android.AndroidEntryPoint
import org.linus.base.presentation.ui.activity.BaseActivity
import org.linus.lms.auth.R

@AndroidEntryPoint
class AuthActivity: BaseActivity(){

    override fun layoutId() = R.layout.activity_auth

}