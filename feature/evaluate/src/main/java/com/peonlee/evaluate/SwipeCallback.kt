package com.peonlee.evaluate

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.peonlee.core.ui.extensions.dpToPx
import com.peonlee.core.ui.R.color as Color
import com.peonlee.core.ui.R.drawable as Drawable
import com.peonlee.evaluate.R.string as String

class SwipeCallback(
    private val context: Context,
    private val swipeCallbackListener: SwipeCallbackListener
) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = UN_USE
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        return makeMovementFlags(
            dragFlags,
            swipeFlags
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {
        swipeCallbackListener.onSwipe(
            viewHolder.absoluteAdapterPosition,
            direction
        )
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        drawSwipeBackground(
            canvas = canvas,
            viewItem = viewHolder.itemView,
            dX = dX
        )
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawSwipeBackground(
        canvas: Canvas,
        viewItem: View,
        dX: Float
    ) {
        drawBackgroundColor(canvas, viewItem, dX)
        drawIcon(canvas, viewItem, dX)
        drawText(canvas, viewItem, dX)
    }

    private fun drawBackgroundColor(
        canvas: Canvas,
        viewItem: View,
        dX: Float
    ) {
        val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(
                context,
                if (dX < LEFT_TO_RIGHT_SWIPE) Color.system_r100 else Color.brand100
            )
        }
        val backgroundRectangle = RectF(
            if (dX < LEFT_TO_RIGHT_SWIPE) viewItem.right.toFloat() + dX else viewItem.left.toFloat(),
            viewItem.top.toFloat(),
            if (dX < LEFT_TO_RIGHT_SWIPE) viewItem.right.toFloat() else viewItem.left.toFloat() + dX,
            viewItem.bottom.toFloat()
        )

        canvas.drawRect(
            backgroundRectangle,
            backgroundPaint
        )
    }

    private fun drawIcon(
        canvas: Canvas,
        viewItem: View,
        dX: Float,
        iconWidth: Int = ICON_SIZE.dpToPx(context),
        sideOffset: Int = ICON_LEFT_RIGHT_MARGIN.dpToPx(context)
    ) {
        ContextCompat.getDrawable(
            context,
            if (dX < LEFT_TO_RIGHT_SWIPE) Drawable.ic_thumbs_down else Drawable.ic_thumbs_up
        )?.apply {
            colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(
                    context,
                    if (dX < LEFT_TO_RIGHT_SWIPE) Color.system_r30 else Color.brand40
                ),
                PorterDuff.Mode.SRC_IN
            )
            bounds = Rect(
                if (dX < LEFT_TO_RIGHT_SWIPE) (viewItem.right + dX.toInt()) + sideOffset else (viewItem.left + dX.toInt() - iconWidth - sideOffset),
                (viewItem.top) + ICON_TOP_BOTTOM_MARGIN.dpToPx(context),
                if (dX < LEFT_TO_RIGHT_SWIPE) (viewItem.right + dX.toInt()) + iconWidth + sideOffset else (viewItem.left + dX.toInt() - sideOffset),
                (viewItem.bottom) - ICON_TOP_BOTTOM_MARGIN.dpToPx(context)
            )
            draw(canvas)
        }
    }

    private fun drawText(
        canvas: Canvas,
        viewItem: View,
        dX: Float,
        iconWidth: Int = ICON_SIZE.dpToPx(context),
        iconMargin: Int = ICON_LEFT_RIGHT_MARGIN.dpToPx(context),
        textMargin: Int = TEXT_LEFT_RIGHT_MARGIN.dpToPx(context)
    ) {
        val textPaint = Paint().apply {
            color = ContextCompat.getColor(
                context,
                if (dX < LEFT_TO_RIGHT_SWIPE) Color.system_r30 else Color.brand40
            )
            textSize = TEXT_SIZE.dpToPx(context).toFloat()
            typeface = Typeface.create(
                Typeface.DEFAULT,
                Typeface.BOLD
            )
        }

        val textMetrics = textPaint.fontMetrics
        val textHeight = viewItem.top + ((viewItem.height - textMetrics.ascent - textMetrics.descent) / 2)

        canvas.drawText(
            if (dX < LEFT_TO_RIGHT_SWIPE) context.getString(String.item_evaluate_negative) else context.getString(String.item_evaluate_positive),
            if (dX < LEFT_TO_RIGHT_SWIPE) {
                (viewItem.right + iconWidth + iconMargin + textMargin) + dX.toInt().toFloat()
            } else {
                val textWidth = textPaint.measureText(context.getString(String.item_evaluate_positive))
                (viewItem.left - iconWidth - iconMargin - textMargin - textWidth) + dX.toInt().toFloat()
            },
            textHeight,
            textPaint
        )
    }

    companion object {
        private const val ICON_SIZE = 20
        private const val ICON_TOP_BOTTOM_MARGIN = 27
        private const val ICON_LEFT_RIGHT_MARGIN = 24

        private const val TEXT_LEFT_RIGHT_MARGIN = 8
        private const val TEXT_SIZE = 14

        private const val UN_USE = 0

        private const val LEFT_TO_RIGHT_SWIPE = 0
    }
}
