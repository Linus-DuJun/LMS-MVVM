package org.linus.base.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.linus.base.presentation.ui.viewmodel.BaseViewModel
import org.linus.base.util.extension.viewLifecycle

abstract class BaseFragment<T: ViewDataBinding> : Fragment() {
    protected var binding: T by viewLifecycle()
    protected lateinit var navController: NavController
    protected abstract val viewModel: BaseViewModel

    protected abstract fun layoutId(): Int
    protected abstract fun initBinding(binding: T, state: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        // Workaround for http://stackoverflow.com/questions/27057449/when-switch-fragment-with-swiperefreshlayout-during-refreshing-fragment-freezes
        return if (binding.root is SwipeRefreshLayout) FrameLayout(requireContext()).apply {
            addView(binding.root)
        } else {
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initBinding(binding = binding, state = savedInstanceState)
//        viewModel.error.observe(viewLifecycleOwner, Observer())
    }

    override fun onDestroyView() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(null)
        super.onDestroyView()
    }
}