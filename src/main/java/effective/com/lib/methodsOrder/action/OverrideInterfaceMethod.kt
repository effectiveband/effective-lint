package effective.com.lib.methodsOrder.action

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.isOverrideInterface
import org.jetbrains.uast.UMethod

class OverrideInterfaceMethod : IMethod {

    override fun calculateWeight(javaContext: JavaContext, method: UMethod, position: Int) =
	if (method.isOverrideInterface()) STEP_WEIGHT * position else -1
}
