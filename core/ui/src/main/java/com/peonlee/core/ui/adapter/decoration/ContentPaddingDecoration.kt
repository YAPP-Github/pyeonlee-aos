package com.peonlee.core.ui.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.peonlee.core.ui.extensions.dpToPx
import com.peonlee.model.util.PaddingValues

class ContentPaddingDecoration(
    private val padding: PaddingValues = PaddingValues()
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = padding.top.dpToPx(view.context)
        outRect.right = padding.right.dpToPx(view.context)
        outRect.bottom = padding.bottom.dpToPx(view.context)
        outRect.left = padding.left.dpToPx(view.context)
    }
}
