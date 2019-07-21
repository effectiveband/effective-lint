package effective.com.lib.methodsOrder.action

import effective.com.lib.methodsOrder.isProtected
import org.jetbrains.uast.UMethod

class ProtectedMethod(private val method: UMethod) : IMethod {

    override fun calculateWeight(position: Int) =
	if (method.isProtected()) STEP_WEIGHT * position else -1
}
