package com.peonlee.core.ui.util.keyboard

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.peonlee.core.ui.util.keyboard.unregister.SimpleUnregister
import com.peonlee.core.ui.util.keyboard.unregister.Unregister

private const val KEYBOARD_MIN_HEIGHT_RATIO = 0.15

interface KeyboardVisibilityEventListener {
    fun onVisibilityChanged(isOpen: Boolean)
}

/**
 * 키보드 show / hide event listener
 */
object KeyboardVisibilityEvent {
    /**
     * listener 등록
     * @param activity keyboard event 를 추적할 activity
     * @param lifecycleOwner listener 를 파괴
     * @param listener event listener
     */
    fun setEventListener(
        activity: Activity,
        lifecycleOwner: LifecycleOwner,
        listener: KeyboardVisibilityEventListener
    ) {
        val unregister = registerEventListener(activity, listener)
        lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                lifecycleOwner.lifecycle.removeObserver(this)
                unregister.unregister()
            }
        })
    }

    private fun registerEventListener(
        activity: Activity,
        listener: KeyboardVisibilityEventListener
    ): Unregister {
        val activityRoot = getActivityRoot(activity)
        val layoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            private var wasOpened = false
            override fun onGlobalLayout() {
                val isOpen = isKeyboardVisible(activity)
                if (isOpen == wasOpened) return
                wasOpened = isOpen
                listener.onVisibilityChanged(wasOpened)
            }
        }

        activityRoot.viewTreeObserver.addOnGlobalLayoutListener(layoutListener)
        return SimpleUnregister(activity, layoutListener)
    }

    fun isKeyboardVisible(activity: Activity): Boolean {
        val rect = Rect()
        val activityRoot = getActivityRoot(activity)
        activityRoot.getWindowVisibleDisplayFrame(rect)

        val location = IntArray(2)
        getContentRoot(activity).getLocationOnScreen(location)

        val screenHeight = activityRoot.rootView.height
        val heightDiff = screenHeight - rect.height() - location[1]

        return heightDiff > screenHeight * KEYBOARD_MIN_HEIGHT_RATIO
    }

    fun getActivityRoot(activity: Activity): View {
        return getContentRoot(activity).rootView
    }

    private fun getContentRoot(activity: Activity): ViewGroup {
        return activity.findViewById(android.R.id.content)
    }
}
