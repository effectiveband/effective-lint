package effective.com.lib.registry

import com.android.tools.lint.client.api.IssueRegistry
import effective.com.lib.issue.LayoutNameIssue
import effective.com.lib.issue.XmlIdIssue

class CustomLintRegistry : IssueRegistry() {

    override val issues = listOf(XmlIdIssue.ISSUE, LayoutNameIssue.ISSUE)
}
