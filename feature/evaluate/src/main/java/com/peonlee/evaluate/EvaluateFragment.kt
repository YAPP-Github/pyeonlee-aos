package com.peonlee.evaluate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.evaluate.databinding.FragmentEvaluateBinding

class EvaluateFragment : BaseFragment<FragmentEvaluateBinding>() {
    override fun bindingFactory(parent: ViewGroup): FragmentEvaluateBinding {
        return FragmentEvaluateBinding.inflate(layoutInflater, parent, false)
    }

    override fun initViews() {

    }
}
