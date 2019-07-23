package effective.com.lib.methodsOrder.action

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.isPublic
import org.jetbrains.uast.UMethod

class PublicMethod : IMethod {

    override fun calculateWeight(javaContext: JavaContext, method: UMethod, position: Int) =
	if (method.isPublic()) STEP_WEIGHT * position else -1
}
