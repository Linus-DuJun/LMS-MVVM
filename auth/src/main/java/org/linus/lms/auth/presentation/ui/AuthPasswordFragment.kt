package org.linus.lms.auth.presentation.ui

import android.os.Bundle
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.linus.base.presentation.ui.fragment.BaseFragment
import org.linus.base.presentation.ui.toast.Toaster
import org.linus.base.util.extension.KEY_USER_ID
import org.linus.base.util.extension.dataStore
import org.linus.base.util.extension.observe
import org.linus.lms.auth.R
import org.linus.lms.auth.databinding.FragmentPasswordBinding
import org.linus.lms.auth.presentation.navigation.AuthNavigator
import org.linus.lms.auth.presentation.viewmodel.AuthViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AuthPasswordFragment: BaseFragment<FragmentPasswordBinding>() {

    @Inject
    lateinit var navigator: AuthNavigator
    @Inject
    lateinit var toaster: Toaster

    override val viewModel by activityViewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toaster.showToast("Email is ${viewModel.email.value}")
        viewModel.apply {
            observe(userLoggedInEvent) {
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