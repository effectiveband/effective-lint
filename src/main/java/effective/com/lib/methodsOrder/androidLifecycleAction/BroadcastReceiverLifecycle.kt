package effective.com.lib.methodsOrder.androidLifecycleAction

import effective.com.lib.utils.BROADCASTRECEIVER_METHODS

class BroadcastReceiverLifecycle : IAndroidLifecycle {

    override fun getAllMethods(): List<String> = BROADCASTRECEIVER_METHODS
}