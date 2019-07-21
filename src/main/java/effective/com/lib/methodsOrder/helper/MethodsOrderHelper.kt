package effective.com.lib.methodsOrder.helper

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.action.AndroidLifecycleMethod
import effective.com.lib.methodsOrder.action.IMethod
import effective.com.lib.methodsOrder.action.OverrideBaseClassMethod
import effective.com.lib.methodsOrder.action.OverrideInterfaceMethod
import effective.com.lib.methodsOrder.action.PrivateMethod
import effective.com.lib.methodsOrder.action.ProtectedAbstractMethod
import effective.com.lib.methodsOrder.action.ProtectedMethod
import effective.com.lib.methodsOrder.action.ProtectedOpenMethod
import effective.com.lib.methodsOrder.action.PublicAbstractMethod
import effective.com.lib.methodsOrder.action.PublicMethod
import effective.com.lib.methodsOrder.action.PublicOpenMethod
import effective.com.lib.methodsOrder.config.MethodOrderConfig
import effective.com.lib.utils.AndroidKotlinMethod
import effective.com.lib.utils.AndroidKotlinMethod.OVERRIDE_ANDROID_LIFECYCLE
import effective.com.lib.utils.AndroidKotlinMethod.OVERRIDE_BASE_CLASS
import effective.com.lib.utils.AndroidKotlinMethod.OVERRIDE_INTERFACE
import effective.com.lib.utils.AndroidKotlinMethod.PUBLIC_ABSTRACT
import effective.com.lib.utils.AndroidKotlinMethod.PROTECTED_ABSTRACT
import effective.com.lib.utils.AndroidKotlinMethod.PUBLIC_OPEN
import effective.com.lib.utils.AndroidKotlinMethod.PROTECTED_OPEN
import effective.com.lib.utils.AndroidKotlinMethod.PUBLIC
import effective.com.lib.utils.AndroidKotlinMethod.PROTECTED
import effective.com.lib.utils.AndroidKotlinMethod.PRIVATE
import org.jetbrains.uast.UMethod

internal class MethodsOrderHelper(
    config: MethodOrderConfig, javaContext: JavaContext, method: UMethod
) {

    private val methodOrderList = config.set.reversed()
    private val map: HashMap<AndroidKotlinMethod, IMethod> = hashMapOf(
	OVERRIDE_ANDROID_LIFECYCLE to AndroidLifecycleMethod(method),
	OVERRIDE_BASE_CLASS to OverrideBaseClassMethod(method, javaContext),
	OVERRIDE_INTERFACE to OverrideInterfaceMethod(method, javaContext),
	PUBLIC_ABSTRACT to PublicAbstractMethod(method),
	PROTECTED_ABSTRACT to ProtectedAbstractMethod(method),
	PUBLIC_OPEN to PublicOpenMethod(method),
	PROTECTED_OPEN to ProtectedOpenMethod(method),
	PUBLIC to PublicMethod(method),
	PROTECTED to ProtectedMethod(method),
	PRIVATE to PrivateMethod(method)
    )

    fun getMethodWeight(): Int {
	var weight = 0
	methodOrderList.forEachIndexed { index, androidKotlinMethod ->
	    val value = map[androidKotlinMethod]?.calculateWeight(index)
	    if (value != null && value > -1) {
		weight += value
		return@forEachIndexed
	    }
	}
	return weight
    }
}
