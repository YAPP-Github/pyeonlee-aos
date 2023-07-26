package com.peonlee.user.modify

import android.content.Context
import android.content.Intent
import android.text.InputFilter
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.extensions.showToast
import com.peonlee.user.R
import com.peonlee.user.databinding.ActivityModifyUserNicknameBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * 사용자의 닉네임을 변경할 수 있는 Activity
 */
@AndroidEntryPoint
class ModifyUserNicknameActivity : BaseActivity<ActivityModifyUserNicknameBinding>() {
    private val modifyUserNicknameViewModel: ModifyUserNicknameViewModel by viewModels()
    override fun bindingFactory(): ActivityModifyUserNicknameBinding {
        return ActivityModifyUserNicknameBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        if (modifyUserNicknameViewModel.userId == null) finish()
        if (modifyUserNicknameViewModel.originalNickname == null) finish()
        with(binding) {
            btnBack.setOnClickListener { finish() }
            btnSave.setOnClickListener { modifyUserNicknameViewModel.saveNickname() }
            btnCancel.setOnClickListener {
                binding.editNickname.setText("")
                modifyUserNicknameViewModel.clearNickname()
            }
            binding.editNickname.setText(modifyUserNicknameViewModel.originalNickname)
            editNickname.filters = arrayOf( // 최대 길이 제한
                InputFilter.LengthFilter(15)
            )
            editNickname.doOnTextChanged { text, _, _, _ -> modifyUserNicknameViewModel.setNickname(text.toString()) }
        }
    }

    override fun bindViews() {
        modifyUserNicknameViewModel.nickname.flowWithLifecycle(lifecycle)
            .onEach {
                binding.txtNickNameLength.text = getString(R.string.nickname_length, it.length)
            }.launchIn(lifecycleScope)
        modifyUserNicknameViewModel.uiEvent.flowWithLifecycle(lifecycle)
            .onEach {
                when (it) {
                    ModifyUserNicknameUiEvent.Success -> finish()
                    is ModifyUserNicknameUiEvent.Toast -> showToast(it.message)
                }
            }.launchIn(lifecycleScope)
    }

    companion object {
        const val USER_NICKNAME = "user_nickname"
        const val USER_ID = "user_identifier"

        fun getIntent(
            context: Context,
            userNickname: String,
            userId: Int
        ): Intent {
            val intent = Intent(context, ModifyUserNicknameActivity::class.java)
            intent.putExtra(USER_NICKNAME, userNickname)
            intent.putExtra(USER_ID, userId)

            return intent
        }
    }
}
