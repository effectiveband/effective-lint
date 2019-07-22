package effective.com.lib.methodsOrder.helper

import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.config.MethodOrderConfig
import org.jetbrains.uast.UMethod

internal class MethodsOrderHelper(
    config: MethodOrderConfig, private val javaContext: JavaContext, private val method: UMethod
) {

    private val methodOrderList = config.linkedHash.reversed()

    fun getMethodWeight(): Int {
	var weight = 0
	methodOrderList.forEachIndexed { index, androidKotlinMethod ->
	    val instance = androidKotlinMethod.objectInstance?: androidKotlinMethod.java.newInstance()
	    val value = instance?.calculateWeight(javaContext, method, index)
	    if (value != null && value > -1) {
		weight += value
		return@forEachIndexed
	    }
	}
	return weight
    }
}
