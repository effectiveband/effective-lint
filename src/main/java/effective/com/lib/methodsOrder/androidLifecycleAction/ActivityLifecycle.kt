package effective.com.lib.methodsOrder.androidLifecycleAction

import effective.com.lib.utils.ACTIVITY_METHODS

class ActivityLifecycle : IAndroidLifecycle {

    companion object {
        const val TAG = "Activity"
    }

    override fun getAllMethods(): List<String> = ACTIVITY_METHODS
}
