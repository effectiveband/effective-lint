package effective.com.lib.methodsOrder.androidLifecycleAction

import effective.com.lib.utils.ACTIVITY_METHODS

class ActivityLifecycle : IAndroidLifecycle {

    override fun getAllMethods(): List<String> = ACTIVITY_METHODS
}
