package effective.com.lib.methodsOrder.action

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.isProtected
import org.jetbrains.uast.UMethod

class ProtectedMethod : IMethod {

    override fun calculateWeight(javaContext: JavaContext, method: UMethod, position: Int) =
	if (method.isProtected()) STEP_WEIGHT * position else -1
}
