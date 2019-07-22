package effective.com.lib.methodsOrder.androidLifecycleAction

import effective.com.lib.utils.BROADCASTRECEIVER_METHODS

class BroadcastReceiverLifecycle : IAndroidLifecycle {

    companion object TAG {
        const val TAG = "BroadcastReceiver"
    }

    override fun getAllMethods(): List<String> = BROADCASTRECEIVER_METHODS
}