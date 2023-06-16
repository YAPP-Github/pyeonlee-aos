package com.peonlee.core.ui.util

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.lang.ref.WeakReference

private const val KEYBOARD_MIN_HEIGHT_RATIO = 0.15

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

    fun registerEventListener(
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

interface KeyboardVisibilityEventListener {
    fun onVisibilityChanged(isOpen: Boolean)
}

interface Unregister {
    fun unregister()
}

class SimpleUnregister(
    activity: Activity,
    globalLayoutListener: OnGlobalLayoutListener
) : Unregister {
    private val activityWeakReference: WeakReference<Activity> = WeakReference(activity)

    private val onGlobalLayoutListenerWeakReference: WeakReference<ViewTreeObserver.OnGlobalLayoutListener> =
        WeakReference(globalLayoutListener)

    override fun unregister() {
        val activity = activityWeakReference.get()
        val globalLayoutListener = onGlobalLayoutListenerWeakReference.get()

        if (null != activity && null != globalLayoutListener) {
            val activityRoot = KeyboardVisibilityEvent.getActivityRoot(activity)
            activityRoot.viewTreeObserver
                .removeGlobalOnLayoutListener(globalLayoutListener)
        }

        activityWeakReference.clear()
        onGlobalLayoutListenerWeakReference.clear()
    }
}
