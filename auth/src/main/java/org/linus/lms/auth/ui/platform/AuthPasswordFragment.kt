package org.linus.lms.auth.ui.platform

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.linus.base.platform.ui.fragment.BaseFragment
import org.linus.lms.auth.R
import org.linus.lms.auth.databinding.FragmentPasswordBinding
import org.linus.lms.auth.ui.viewmodel.AuthViewModel

@AndroidEntryPoint
class AuthPasswordFragment: BaseFragment<FragmentPasswordBinding>() {

    override val viewModel by activityViewModels<AuthViewModel>()

    override fun layoutId() = R.layout.fragment_password

    override fun initBinding(binding: FragmentPasswordBinding, state: Bundle?) {
        binding.viewmodel = viewModel
    }
}