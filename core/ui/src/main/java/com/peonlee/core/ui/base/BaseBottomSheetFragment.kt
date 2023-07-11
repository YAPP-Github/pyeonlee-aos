package com.peonlee.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peonlee.core.ui.databinding.BaseBottomsheetDialogBinding

/**
 * BottomSheetFragment 공통 코드 관리
 */
abstract class BaseBottomSheetFragment(
    private val title: String
) : BottomSheetDialogFragment() {

    private lateinit var binding: BaseBottomsheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BaseBottomsheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = title
        // 하위 클래스에 따른 filter layout 추가
        binding.layoutFilter.addView(getFilterLayout())
    }

    abstract fun getFilterLayout(): View
}
