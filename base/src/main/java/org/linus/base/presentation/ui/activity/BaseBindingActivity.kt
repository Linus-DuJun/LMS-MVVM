package org.linus.base.presentation.ui.activity

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<T: ViewDataBinding> : BaseActivity() {

    protected lateinit var binding: T

    protected abstract fun initBinding(binding: T)

    override fun setContentView(view: View?) {
        binding = DataBindingUtil.setContentView(this, layoutId())
        binding.lifecycleOwner = this
        initBinding(binding = binding)
    }
}