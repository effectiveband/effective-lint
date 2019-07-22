package effective.com.lib.methodsOrder.action

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.isOverrideBaseClass
import org.jetbrains.uast.UMethod

class OverrideBaseClassMethod : IMethod {

    override fun calculateWeight(javaContext: JavaContext, method: UMethod, position: Int) =
	if (method.isOverrideBaseClass(javaContext.evaluator)) STEP_WEIGHT * position else -1
}
