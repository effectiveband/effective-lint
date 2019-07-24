package effective.com.lib.detector

import com.android.tools.lint.detector.api.LayoutDetector
import com.android.tools.lint.detector.api.Project
import com.android.tools.lint.detector.api.XmlContext
import com.android.tools.lint.detector.api.computeResourcePrefix
import effective.com.lib.AndroidXmlUtils
import effective.com.lib.issue.LayoutNameIssue
import org.w3c.dom.Document

class LayoutNameDetector : LayoutDetector() {

    override fun visitDocument(context: XmlContext, document: Document) {
        val modified = AndroidXmlUtils.ALLOWED_PREFIXES.map {
            val resourcePrefix = context.project.resourcePrefix().forceUnderscoreIfNeeded()

            if (resourcePrefix != it) resourcePrefix + it else it
        }

        val doesNotStartWithPrefix = modified.none { context.file.name.startsWith(it) }
        val notEquals = modified.map {
            it.dropLast(1)
        }.none { context.file.name == "$it.xml" }

        if (doesNotStartWithPrefix && notEquals) {
            context.report(
                LayoutNameIssue.ISSUE,
                document,
                context.getLocation(document),
                "Layout does not start with one of the following prefixes: ${modified.joinToString()}"
            )
        }
    }

    private fun Project.resourcePrefix() =
        if (isGradleProject) computeResourcePrefix(gradleProjectModel).orEmpty() else ""

    private fun String.forceUnderscoreIfNeeded() = if (isNotEmpty() && !endsWith("_")) plus("_") else this
}