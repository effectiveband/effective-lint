package effective.com.lib.registry

import com.android.tools.lint.client.api.IssueRegistry
import effective.com.lib.issue.LayoutNameIssue
import effective.com.lib.issue.XmlIdIssue
import effective.com.lib.methodsOrder.issue.MethodsOrderIssue

class CustomLintRegistry : IssueRegistry() {

    override val issues = listOf(
        XmlIdIssue.ISSUE, LayoutNameIssue.ISSUE, MethodsOrderIssue.ISSUE
    )
}
