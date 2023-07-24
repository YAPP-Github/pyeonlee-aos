package com.peonlee

import android.content.Intent
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.settings.R.string as String
import com.peonlee.settings.databinding.FragmentSettingBinding
import com.peonlee.termsdetail.TermsDetailActivity

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    private val termsList: List<SettingUiModel> = listOf(
        SettingUiModel(getString(String.terms_of_use_service), "111"),
        SettingUiModel(getString(String.agree_personal), "11"),
        SettingUiModel(getString(String.personal_policy), "111"),
        SettingUiModel(getString(String.logout), "")
    )

    override fun bindingFactory(parent: ViewGroup?): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater, parent, false)
    }

    override fun initViews() {
        val adapter = SettingAdapter { settingUiModel ->
            handleEvent(settingUiModel)
        }.apply {
            submitList(termsList)
        }
        binding.rvTermsList.adapter = adapter
    }

    private fun handleEvent(event: SettingUiModel) {
        when(event.url.isEmpty()) {
            true -> {
                val intent = Intent(requireContext(), TermsDetailActivity::class.java).apply {
                    putExtra("title", event.termTitle)
                    putExtra("url", event.url)
                }
                startActivity(intent)
            }
            false -> logout()
        }
    }

    private fun logout() {

    }

    companion object {
        fun getInstance() = SettingFragment()
    }
}
