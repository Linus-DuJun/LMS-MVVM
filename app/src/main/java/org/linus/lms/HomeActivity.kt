package org.linus.lms

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.linus.base.presentation.ui.activity.BaseActivity
import org.linus.base.presentation.ui.toast.Toaster
import org.linus.base.util.extension.KEY_USER_ID
import org.linus.base.util.extension.dataStore
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity: BaseActivity() {

    @Inject
    lateinit var toaster: Toaster

    override fun layoutId() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uid = runBlocking {
            dataStore.data.map {
                it[KEY_USER_ID]
            }.first()
        }
        toaster.showToast("UID IS $uid")
    }
}