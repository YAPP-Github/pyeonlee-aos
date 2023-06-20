package com.peonlee.core.ui.util.keyboard.unregister

import android.app.Activity
import android.view.ViewTreeObserver
import com.peonlee.core.ui.util.keyboard.KeyboardVisibilityEvent
import java.lang.ref.WeakReference

class SimpleUnregister(
    activity: Activity,
    globalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener
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
