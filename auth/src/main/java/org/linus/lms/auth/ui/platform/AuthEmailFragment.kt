package org.linus.lms.auth.ui.platform

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import org.linus.base.platform.ui.fragment.BaseFragment
import org.linus.base.util.extension.observe
import org.linus.lms.auth.R
import org.linus.lms.auth.databinding.FragmentEmailBinding
import org.linus.lms.auth.ui.viewmodel.AuthViewModel

class AuthEmailFragment: BaseFragment<FragmentEmailBinding>() {

    override val viewModel by activityViewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            observe(onContinueEvent) {
                navController.navigate(AuthEmailFragmentDirections.toPasswordFragment())
            }
        }
    }

    override fun layoutId() = R.layout.fragment_email

    override fun initBinding(binding: FragmentEmailBinding, state: Bundle?) {
        binding.viewmodel = viewModel
    }


}