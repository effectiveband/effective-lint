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
import effective.com.lib.methodsOrder.config.MethodOrderConfig
import effective.com.lib.methodsOrder.helper.MethodsOrderHelper
import org.jetbrains.uast.UMethod
import effective.com.lib.utils.AndroidKotlinMethod.OVERRIDE_ANDROID_LIFECYCLE
import effective.com.lib.utils.AndroidKotlinMethod.OVERRIDE_BASE_CLASS
import effective.com.lib.utils.AndroidKotlinMethod.OVERRIDE_INTERFACE
import effective.com.lib.utils.AndroidKotlinMethod.PUBLIC_ABSTRACT
import effective.com.lib.utils.AndroidKotlinMethod.PROTECTED_ABSTRACT
import effective.com.lib.utils.AndroidKotlinMethod.PUBLIC_OPEN
import effective.com.lib.utils.AndroidKotlinMethod.PROTECTED_OPEN
import effective.com.lib.utils.AndroidKotlinMethod.PUBLIC
import effective.com.lib.utils.AndroidKotlinMethod.PROTECTED
import effective.com.lib.utils.AndroidKotlinMethod.PRIVATE
import org.jetbrains.kotlin.psi.KtNamedFunction


class MethodsOrderDetector : Detector(), Detector.UastScanner {

    private var javaContext: JavaContext? = null

    private val list: MutableList<Pair<UMethod, Int>> = mutableListOf()

    override fun beforeCheckFile(context: Context) {
	list.clear()
    }

    override fun afterCheckFile(context: Context) {
	var previewValue = Int.MAX_VALUE

	list.forEach {
	    val value = it.second

	    if (value > previewValue) {
		val uMethod = it.first

		javaContext?.let { javaContext ->
		    javaContext.report(
			MethodsOrderIssue.ISSUE,
			uMethod,
			javaContext.getLocation(uMethod),
			MethodsOrderIssue.ID
		    )
		}
	    }

	    previewValue = value
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
	    setOf(
		OVERRIDE_ANDROID_LIFECYCLE,
		OVERRIDE_BASE_CLASS,
		OVERRIDE_INTERFACE,
		PUBLIC_ABSTRACT,
		PROTECTED_ABSTRACT,
		PUBLIC_OPEN,
		PROTECTED_OPEN,
		PUBLIC,
		PROTECTED,
		PRIVATE
	    )
	)

	override fun visitClass(node: UClass) {
	    if (isKotlin(node) && !node.isInterface) {
		node.methods.filter {
		    it.sourceElement is KtNamedFunction
		}.forEach {
		    MethodsOrderHelper(config, context, it).apply {
			list.add(it to getMethodWeight())
		    }
		}
	    }
	}
    }
}
