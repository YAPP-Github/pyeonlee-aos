package com.peonlee.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peonlee.feature.detail.databinding.BottomSheetManageReviewBinding

class ReviewManageDialog : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetManageReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetManageReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            llEditReview.setOnClickListener {
            }
            llDeleteReview.setOnClickListener {
            }
        }
    }
}
