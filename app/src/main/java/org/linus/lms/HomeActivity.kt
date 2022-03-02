package org.linus.lms

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.linus.base.platform.ui.activity.BaseActivity
import org.linus.base.platform.ui.toast.Toaster
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity: BaseActivity() {

    @Inject
    lateinit var toaster: Toaster

    override fun layoutId() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toaster.showToast("Home Activity!!!")
    }
}