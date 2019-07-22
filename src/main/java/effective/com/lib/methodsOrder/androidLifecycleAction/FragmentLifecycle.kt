package effective.com.lib.methodsOrder.androidLifecycleAction

import effective.com.lib.utils.FRAGMENT_METHODS

class FragmentLifecycle : IAndroidLifecycle {

    companion object TAG {
        const val TAG = "Fragment"
    }

    override fun getAllMethods() = FRAGMENT_METHODS
}
