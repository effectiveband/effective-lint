package effective.com.lib.methodsOrder.action

import effective.com.lib.methodsOrder.getSuperClass
import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.androidLifecycleAction.ActivityLifecycle
import effective.com.lib.methodsOrder.androidLifecycleAction.BroadcastReceiverLifecycle
import effective.com.lib.methodsOrder.androidLifecycleAction.FragmentLifecycle
import effective.com.lib.methodsOrder.androidLifecycleAction.IAndroidLifecycle
import effective.com.lib.methodsOrder.androidLifecycleAction.ServiceLifecycle
import org.jetbrains.uast.UMethod

class AndroidLifecycleMethod : IMethod {

    private val actionMap: HashMap<String, IAndroidLifecycle> = hashMapOf(
	ActivityLifecycle.TAG to ActivityLifecycle(),
	FragmentLifecycle.TAG to FragmentLifecycle(),
	ServiceLifecycle.TAG to ServiceLifecycle(),
	BroadcastReceiverLifecycle.TAG to BroadcastReceiverLifecycle()
    )

    override fun calculateWeight(javaContext: JavaContext, method: UMethod, position: Int): Int {
	val name = method.getSuperClass()?.getName() ?: return -1
	val value = actionMap[name]?.getMethodWeight(method)
	return if (value != null && value > -1) STEP_WEIGHT * position + value else -1
    }
}
