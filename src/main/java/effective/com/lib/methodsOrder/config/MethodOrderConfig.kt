package effective.com.lib.methodsOrder.config

import effective.com.lib.methodsOrder.action.IMethod
import kotlin.reflect.KClass

class MethodOrderConfig(
    val linkedHash: LinkedHashSet<KClass<out IMethod>>
)
