package effective.com.lib.issue

import com.android.tools.lint.detector.api.*
import effective.com.lib.detector.XmlIdDetector

class XmlIdIssue {

    companion object {

        const val ID = "Incorrect view id"
        private const val DESCRIPTION = "Id attribute has invalid format"
        private const val EXPLANATION =
            "The Id should begin with the file name in camelCase and end with the type name of view"
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
            Implementation(XmlIdDetector::class.java, Scope.RESOURCE_FILE_SCOPE)
        )
    }
}
