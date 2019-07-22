package effective.com.lib.methodsOrder.androidLifecycleAction

import effective.com.lib.utils.SERVICE_METHODS

class ServiceLifecycle : IAndroidLifecycle {

    companion object TAG {
	const val TAG = "Service"
    }

    override fun getAllMethods() = SERVICE_METHODS
}