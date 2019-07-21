package effective.com.lib.methodsOrder.action

import effective.com.lib.methodsOrder.isAbstract
import effective.com.lib.methodsOrder.isPublic
import org.jetbrains.uast.UMethod

class PublicAbstractMethod(private val method: UMethod) : IMethod {

    override fun calculateWeight(position: Int) =
	if (method.isAbstract() && method.isPublic()) STEP_WEIGHT * position else -1
}
