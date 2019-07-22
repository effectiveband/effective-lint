package effective.com.lib.methodsOrder.issue

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import effective.com.lib.methodsOrder.detector.MethodsOrderDetector
import java.util.EnumSet

class MethodsOrderIssue {

    companion object {

	const val ID = "Incorrect order"
	private const val DESCRIPTION = "The method is located in the wrong order."
	private const val EXPLANATION =
	    "Methods order: - `override` lifecycle methods in order from lifecycle\n" + "\n" +
		    "- any other `override` methods from base class\n" + "\n" +
		    "- any other `override` methods from interfaces in interfaces order\n" + "\n" +
		    "- public abstract methods\n" + "\n" +
		    "- protected abstract methods\n" + "\n" +
		    "- public open methods\n" + "\n" +
		    "- public methods\n" + "\n" +
		    "- protected open methods\n" + "\n" +
		    "- protected methods\n" + "\n" +
		    "- private methods"
	private const val PRIORITY = 4
	private val CATEGORY = Category.TYPOGRAPHY
	private val SEVERITY = Severity.WARNING

	val ISSUE = Issue.create(
	    ID,
	    DESCRIPTION,
	    EXPLANATION,
	    CATEGORY,
	    PRIORITY,
	    SEVERITY,
	    Implementation(MethodsOrderDetector::class.java, EnumSet.of(Scope.JAVA_FILE))
	)
    }
}