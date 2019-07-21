package effective.com.lib.methodsOrder.action

import effective.com.lib.methodsOrder.isPrivate
import org.jetbrains.uast.UMethod

class PrivateMethod(private val method: UMethod) : IMethod {

    override fun calculateWeight(position: Int) =
	if (method.isPrivate()) STEP_WEIGHT * position else -1
}
