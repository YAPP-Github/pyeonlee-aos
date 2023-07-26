package com.peonlee.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.peonlee.feature.detail.databinding.DialogCancelConfirmBinding

class ConfirmDialogFragment : DialogFragment() {
    private lateinit var binding: DialogCancelConfirmBinding

    private val viewModel: ProductDetailViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogCancelConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.btnConfirm.setOnClickListener {
            viewModel.cancelLikeProduct(viewModel.productDetail.productId)
            dismiss()
        }
    }
}
