package effective.com.lib.methodsOrder.action

import effective.com.lib.methodsOrder.getSuperClass
import com.android.SdkConstants.CLASS_ACTIVITY
import com.android.SdkConstants.CLASS_SERVICE
import com.android.SdkConstants.CLASS_BROADCASTRECEIVER
import com.android.SdkConstants.CLASS_CONTENTPROVIDER
import com.android.SdkConstants.CLASS_FRAGMENT
import effective.com.lib.methodsOrder.androidLifecycleAction.ActivityLifecycle
import effective.com.lib.methodsOrder.androidLifecycleAction.BroadcastReceiverLifecycle
import effective.com.lib.methodsOrder.androidLifecycleAction.FragmentLifecycle
import effective.com.lib.methodsOrder.androidLifecycleAction.IAndroidLifecycle
import effective.com.lib.methodsOrder.androidLifecycleAction.ServiceLifecycle
import org.jetbrains.uast.UMethod

class AndroidLifecycleMethod(private val method: UMethod) : IMethod {

    private val actionMap: HashMap<String, IAndroidLifecycle> = hashMapOf(
	CLASS_ACTIVITY to ActivityLifecycle(),
	CLASS_FRAGMENT to FragmentLifecycle(),
	CLASS_SERVICE to ServiceLifecycle(),
	CLASS_BROADCASTRECEIVER to BroadcastReceiverLifecycle()
    )

    override fun calculateWeight(position: Int): Int {
	val name = method.getSuperClass()?.getQualifiedName() ?: return -1
	val value = actionMap[name]?.getMethodWeight(method)
	return if (value != null && value > -1) STEP_WEIGHT * position + value else -1
    }
}
