package effective.com.lib.methodsOrder.action

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.isAbstract
import effective.com.lib.methodsOrder.isProtected
import org.jetbrains.uast.UMethod

class ProtectedAbstractMethod : IMethod {

    override fun calculateWeight(javaContext: JavaContext, method: UMethod, position: Int) =
	if (method.isAbstract() && method.isProtected()) STEP_WEIGHT * position else -1
}
