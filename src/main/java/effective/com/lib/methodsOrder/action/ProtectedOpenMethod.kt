package effective.com.lib.methodsOrder.action

import effective.com.lib.methodsOrder.isOpen
import effective.com.lib.methodsOrder.isProtected
import org.jetbrains.uast.UMethod

class ProtectedOpenMethod(private val method: UMethod) : IMethod {

    override fun calculateWeight(position: Int) =
	if (method.isOpen() && method.isProtected()) STEP_WEIGHT * position else -1
}
