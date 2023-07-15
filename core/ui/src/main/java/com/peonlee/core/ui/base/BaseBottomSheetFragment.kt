package com.peonlee.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peonlee.core.ui.R
import com.peonlee.core.ui.databinding.BaseBottomsheetDialogBinding
import com.peonlee.model.product.ProductSearchConditionUiModel


/**
 * BottomSheetFragment 공통 코드 관리
 */
abstract class BaseBottomSheetFragment(
    private val title: String
) : BottomSheetDialogFragment() {

    private var binding: BaseBottomsheetDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BaseBottomsheetDialogBinding.inflate(inflater, container, false)
        binding?.layoutFilter?.run {
            addView(
                getFilterLayout(inflater, this)
            )
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setDimAmount(0.6f)

        // 하위 클래스에 따른 filter layout 추가
        binding?.apply {
            tvTitle.text = title
            btnComplete.setOnClickListener { onClickComplete() }
        }
    }

    override fun getTheme(): Int {
        return R.style.RoundedBottomSheetDialog
    }

    abstract fun getFilterLayout(layoutInflater: LayoutInflater, parent: ViewGroup): View

    abstract fun onClickComplete()

    // 변경된 filter 값 변경
    abstract fun setChangedFilter(productSearchCondition: ProductSearchConditionUiModel): BaseBottomSheetFragment

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
