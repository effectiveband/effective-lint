package effective.com.lib.methodsOrder.action

import com.android.tools.lint.detector.api.JavaContext
import org.jetbrains.uast.UMethod

interface IMethod {

    fun calculateWeight(javaContext: JavaContext, method: UMethod, position: Int): Int
}
