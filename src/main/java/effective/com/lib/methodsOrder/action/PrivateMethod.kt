package effective.com.lib.methodsOrder.action

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.isPrivate
import org.jetbrains.uast.UMethod

class PrivateMethod : IMethod {

    override fun calculateWeight(javaContext: JavaContext, method: UMethod, position: Int) =
	if (method.isPrivate()) STEP_WEIGHT * position else -1
}
