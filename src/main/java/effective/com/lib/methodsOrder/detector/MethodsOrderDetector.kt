package effective.com.lib.methodsOrder.detector

import com.android.tools.lint.detector.api.Context
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import effective.com.lib.methodsOrder.issue.MethodsOrderIssue
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement
import java.util.Collections.singletonList
import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.isKotlin
import effective.com.lib.methodsOrder.action.AndroidLifecycleMethod
import effective.com.lib.methodsOrder.action.OverrideBaseClassMethod
import effective.com.lib.methodsOrder.action.OverrideInterfaceMethod
import effective.com.lib.methodsOrder.action.PrivateMethod
import effective.com.lib.methodsOrder.action.ProtectedAbstractMethod
import effective.com.lib.methodsOrder.action.ProtectedMethod
import effective.com.lib.methodsOrder.action.ProtectedOpenMethod
import effective.com.lib.methodsOrder.action.PublicAbstractMethod
import effective.com.lib.methodsOrder.action.PublicMethod
import effective.com.lib.methodsOrder.action.PublicOpenMethod
import effective.com.lib.methodsOrder.config.MethodOrderConfig
import effective.com.lib.methodsOrder.helper.MethodsOrderHelper
import org.jetbrains.uast.UMethod
import org.jetbrains.kotlin.psi.KtNamedFunction


class MethodsOrderDetector : Detector(), Detector.UastScanner {

    private var javaContext: JavaContext? = null

    private val list: MutableList<MutableList<Pair<UMethod, Int>>> = mutableListOf()

    override fun beforeCheckFile(context: Context) {
	list.clear()
    }

    override fun afterCheckFile(context: Context) {
	if (list.isEmpty()) return

	list.forEach {
	    if (it.isNotEmpty()) {
		val subList = it
		var previewValue = Int.MAX_VALUE
		var previewUMethod = it[0].first

		subList.forEach { pairMethodWeight ->
		    val (uMethod, value) = pairMethodWeight

		    if (value > previewValue) {

			javaContext?.let { javaContext ->
			    javaContext.report(
				MethodsOrderIssue.ISSUE,
				previewUMethod,
				javaContext.getLocation(previewUMethod),
				MethodsOrderIssue.ID
			    )
			}
		    }

		    previewUMethod = uMethod
		    previewValue = value
		}
	    }
	}

	list.clear()
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? =
	singletonList(UClass::class.java)

    override fun createUastHandler(context: JavaContext): CustomVisitor {
	javaContext = context
	return CustomVisitor(context)
    }

    inner class CustomVisitor(private val context: JavaContext) : UElementHandler() {

	private val config = MethodOrderConfig(
	    linkedSetOf(
		AndroidLifecycleMethod::class,
		OverrideBaseClassMethod::class,
		OverrideInterfaceMethod::class,
		PublicAbstractMethod::class,
		ProtectedAbstractMethod::class,
		PublicOpenMethod::class,
		PublicMethod::class,
		ProtectedOpenMethod::class,
		ProtectedMethod::class,
		PrivateMethod::class
	    )
	)

	override fun visitClass(node: UClass) {
	    if (isKotlin(node) && !node.isInterface) {
		val subList: MutableList<Pair<UMethod, Int>> = mutableListOf()

		node.methods.filter {
		    it.sourceElement is KtNamedFunction
		}.forEach {
		    MethodsOrderHelper(config, context, it).apply {
			subList.add(it to getMethodWeight())
		    }
		}

		list.add(subList)
	    }
	}
    }
}
