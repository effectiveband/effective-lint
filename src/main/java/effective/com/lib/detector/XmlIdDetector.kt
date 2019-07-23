package effective.com.lib.detector

import com.android.tools.lint.detector.api.*
import effective.com.lib.issue.XmlIdIssue
import com.android.tools.lint.detector.api.XmlContext
import effective.com.lib.validator.ViewIdValidator
import org.w3c.dom.Attr


class XmlIdDetector : LayoutDetector() {

    override fun getApplicableElements(): List<String> = XmlScannerConstants.ALL

    override fun getApplicableAttributes(): Collection<String> = listOf("id")

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val element = attribute.ownerElement
        val viewIdValidator = ViewIdValidator(context.file.name, element)
        if (!viewIdValidator.validateId()) {
            val quickFix = LintFix.create()
                .replace()
                .name(ViewIdValidator.FIX_DISPLAY_NAME)
                .text(viewIdValidator.valueId)
                .with(viewIdValidator.getExpectedResult())
                .build()
            context.report(
                XmlIdIssue.ISSUE,
                context.getLocation(attribute),
                XmlIdIssue.ID,
                quickFix
            )
        }
    }
}
