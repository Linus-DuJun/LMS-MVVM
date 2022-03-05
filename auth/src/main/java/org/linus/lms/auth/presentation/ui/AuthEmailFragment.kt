package org.linus.lms.auth.presentation.ui

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.linus.base.domain.exception.AppException
import org.linus.base.presentation.ui.fragment.BaseFragment
import org.linus.base.presentation.ui.toast.Toaster
import org.linus.base.util.extension.observe
import org.linus.lms.auth.R
import org.linus.lms.auth.databinding.FragmentEmailBinding
import org.linus.lms.auth.presentation.viewmodel.AuthViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AuthEmailFragment: BaseFragment<FragmentEmailBinding>() {

    @Inject
    lateinit var toaster: Toaster

    override val viewModel by activityViewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            observe(openVerifyCodeScreenEvent) { openVerifyCodeScreen() }
            observe(confirmAccountEvent, ::onAccountNeedVerify)
            observe(accountVerifyFailedEvent, ::onAccountVerifyFailed)
            observe(error) {
                if (it is AppException.EmailNotRegisteredException) {
                    toaster.showToast("Ready to register")
                } else {
                    toaster.showToast("error: ${it.message}")
                }
            }
        }
    }

    override fun layoutId() = R.layout.fragment_email

    override fun initBinding(binding: FragmentEmailBinding, state: Bundle?) {
        binding.viewmodel = viewModel
    }

    private fun openVerifyCodeScreen() {
        navController.navigate(AuthEmailFragmentDirections.toPasswordFragment())
    }

    private fun onAccountNeedVerify(email: String) {
        toaster.showToast("$email need confirm to continue")
    }

    private fun onAccountVerifyFailed(reason: String) {
        toaster.showToast(reason)
    }
}