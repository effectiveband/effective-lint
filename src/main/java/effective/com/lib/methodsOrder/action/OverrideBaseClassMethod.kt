package effective.com.lib.methodsOrder.action

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.isOverrideBaseClass
import org.jetbrains.uast.UMethod

class OverrideBaseClassMethod(
    private val method: UMethod, private val javaContext: JavaContext
) : IMethod {

    override fun calculateWeight(position: Int) =
	if (method.isOverrideBaseClass(javaContext.evaluator)) STEP_WEIGHT * position else -1
}
