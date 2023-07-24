package com.peonlee

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.data.local.LocalDataSource
import com.peonlee.login.LoginActivity
import com.peonlee.settings.R.string as String
import com.peonlee.settings.databinding.FragmentSettingBinding
import com.peonlee.termsdetail.TermsDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    @Inject
    lateinit var dataStore: LocalDataSource

    override fun bindingFactory(parent: ViewGroup?): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater, parent, false)
    }

    override fun initViews() = with(binding){
        val termsList: List<SettingUiModel> = listOf(
            SettingUiModel(getString(String.terms_of_use_service), "111"),
            SettingUiModel(getString(String.agree_personal), "11"),
            SettingUiModel(getString(String.personal_policy), "111"),
            SettingUiModel(getString(String.logout), "")
        )

        val settingAdapter = SettingAdapter { settingUiModel ->
            handleEvent(settingUiModel)
        }.apply {
            submitList(termsList)
        }
        rvTermsList.adapter = settingAdapter

        ivSettingClose.setOnClickListener {  }
    }

    private fun handleEvent(event: SettingUiModel) {
        when(event.url.isNotEmpty()) {
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
        lifecycleScope.launch {
            dataStore.setAccessToken("")
        }
        val intent = Intent(requireActivity(), LoginActivity::class.java).apply {
            flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        requireActivity().finish()
    }

    companion object {
        fun getInstance() = SettingFragment()
    }
}
