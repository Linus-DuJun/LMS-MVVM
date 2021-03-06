package org.linus.lms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.linus.lms.auth.presentation.ui.AuthActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_SCREEN_DURATION = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            withTimeout(SPLASH_SCREEN_DURATION) {
                Intent(this@MainActivity, AuthActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

}