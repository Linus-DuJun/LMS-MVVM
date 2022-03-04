package org.linus.lms.auth.ui.platform

import android.os.Bundle
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.linus.base.platform.ui.fragment.BaseFragment
import org.linus.base.util.extension.KEY_USER_ID
import org.linus.base.util.extension.dataStore
import org.linus.base.util.extension.observe
import org.linus.lms.auth.R
import org.linus.lms.auth.databinding.FragmentPasswordBinding
import org.linus.lms.auth.ui.navigation.AuthNavigator
import org.linus.lms.auth.ui.viewmodel.AuthViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AuthPasswordFragment: BaseFragment<FragmentPasswordBinding>() {

    @Inject
    lateinit var navigator: AuthNavigator

    override val viewModel by activityViewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            observe(onUserLoggedInEvent) {
                saveUid()
                navigateToHome()
            }
        }
    }
    override fun layoutId() = R.layout.fragment_password

    override fun initBinding(binding: FragmentPasswordBinding, state: Bundle?) {
        binding.viewmodel = viewModel
    }

    private fun saveUid() {
        lifecycleScope.launch {
            requireContext().dataStore.edit { settings ->
                settings[KEY_USER_ID] = "linus"
            }
        }
    }

    private fun navigateToHome() {
        navigator.toHomeActivity()
        requireActivity().finish()
    }
}