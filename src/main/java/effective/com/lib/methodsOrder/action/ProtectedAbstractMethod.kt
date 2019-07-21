package effective.com.lib.methodsOrder.action

import effective.com.lib.methodsOrder.isAbstract
import effective.com.lib.methodsOrder.isProtected
import org.jetbrains.uast.UMethod

class ProtectedAbstractMethod(private val method: UMethod) : IMethod {

    override fun calculateWeight(position: Int) =
	if (method.isAbstract() && method.isProtected()) STEP_WEIGHT * position else -1
}
