package effective.com.lib.issue

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope.Companion.RESOURCE_FILE_SCOPE
import com.android.tools.lint.detector.api.Severity
import effective.com.lib.detector.LayoutNameDetector
import effective.com.lib.AndroidXmlUtils


class LayoutNameIssue {

    companion object {

        private const val ID = "Wrong layout name"
        private const val DESCRIPTION = "Layout names should be prefixed accordingly"
        private val EXPLANATION = "The layout file name should be prefixed with one of the following: " +
                "${AndroidXmlUtils.ALLOWED_PREFIXES.joinToString()}. \n " +
                "This will improve consistency in your code base as well as enforce a certain structure"
        private const val PRIORITY = 4
        private val CATEGORY = Category.CORRECTNESS
        private val SEVERITY = Severity.WARNING

        val ISSUE = Issue.create(
            ID,
            DESCRIPTION,
            EXPLANATION,
            CATEGORY,
            PRIORITY,
            SEVERITY,
            Implementation(LayoutNameDetector::class.java, RESOURCE_FILE_SCOPE)
        )
    }
}
