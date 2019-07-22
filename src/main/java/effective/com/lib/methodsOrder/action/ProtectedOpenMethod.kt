package effective.com.lib.methodsOrder.action

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.isOpen
import effective.com.lib.methodsOrder.isProtected
import org.jetbrains.uast.UMethod

class ProtectedOpenMethod : IMethod {

    override fun calculateWeight(javaContext: JavaContext, method: UMethod, position: Int) =
	if (method.isOpen() && method.isProtected()) STEP_WEIGHT * position else -1
}
