package effective.com.lib.validator

import com.google.common.annotations.VisibleForTesting
import effective.com.lib.AndroidXmlUtils.ALLOWED_PREFIXES
import effective.com.lib.AndroidXmlUtils.ALL_ANDROID_WIDGETS
import effective.com.lib.AndroidXmlUtils.IGNORE_PREFIXES
import effective.com.lib.utils.removeAfterCharacter
import effective.com.lib.utils.removeBeforeCharacter
import effective.com.lib.utils.toCamelCase
import org.w3c.dom.Element


internal class ViewIdValidator(fullFileName: String, element: Element) {

    companion object {
        const val FIX_DISPLAY_NAME = "Fix incorrect id"
        private const val SCHEMA = "http://schemas.android.com/apk/res/android"
        private const val FILE_NAME_DELIMITER = '_'
        private const val ID = "id"
    }

    val valueId = element.getAttributeNS(SCHEMA, ID).orEmpty()
    private val fileName = getFileNameWithoutPrefix(getFileNameWithoutExtension(fullFileName))
    private val valueIdWithoutDefinition = valueId.removeBeforeCharacter('/')
    private val viewName = getViewType(element.tagName)
    private val fileNameInCamelCase = fileName.toCamelCase(false, FILE_NAME_DELIMITER)

    fun validateId(): Boolean = isValidateId()

    fun getExpectedResult(): String {
        val startDefinition = getIdStartDefinition() + "/"
        val content = valueIdWithoutDefinition.substring(getStartContentIndex(), getEndContentIndex()).capitalize()
        return "$startDefinition$fileNameInCamelCase$content$viewName"
    }

    @VisibleForTesting
    public fun getFileNameWithoutPrefix(fullFileName: String): String {
        val fileNameInLowerCase = fullFileName.toLowerCase()
        ALLOWED_PREFIXES.forEach {
            if (fileNameInLowerCase.indexOf(it) == 0) {
                return fullFileName.substring(it.length)
            }
        }
        return fullFileName
    }

    @VisibleForTesting
    public fun getFileNameWithoutExtension(fileName: String) = fileName.removeAfterCharacter('.')

    private fun getViewType(tagName: String) = tagName.removeBeforeCharacter('.')

    private fun isValidateId(): Boolean {
        val regex = "^@.?$ID/$fileNameInCamelCase(.{0}|[._\\p{Lu}0-9].*?)$viewName"
        return if (valueId.isEmpty()) true else valueId.matches(Regex(regex))
    }

    private fun getIdStartDefinition() = valueId.removeAfterCharacter('/')

    @VisibleForTesting
    public fun getStartContentIndex(): Int {
        val words = fileName.split(FILE_NAME_DELIMITER)
        val valueIdWithoutDefinitionLowerCase = valueIdWithoutDefinition.toLowerCase()
        val viewIdWithoutPrefix = getStringWithoutPrefix(valueIdWithoutDefinitionLowerCase)
        val differentLength = valueIdWithoutDefinitionLowerCase.length - viewIdWithoutPrefix.length
        var index = 0
        words.forEach {
            if (viewIdWithoutPrefix.indexOf(it.toLowerCase()) == index) {
                index += it.length
            }
        }
        return index + differentLength
    }

    private fun getStringWithoutPrefix(text: String): String {
        IGNORE_PREFIXES.forEach {
            if (text.startsWith(it, ignoreCase = true)) {
                return text.substring(it.length)
            }
        }
        return text
    }

    @VisibleForTesting
    public fun getEndContentIndex(): Int {
        val startViewNameIndex = getStartIsActualViewNameIndex()
        return if (startViewNameIndex != -1) startViewNameIndex else getStartNotActualViewNameIndex()
    }

    @VisibleForTesting
    public fun getStartIsActualViewNameIndex(): Int {
        val valueIdInLowerCase = valueIdWithoutDefinition.toLowerCase()
        val index = valueIdInLowerCase.lastIndexOf(viewName.toLowerCase())
        return if (index + viewName.length == valueIdWithoutDefinition.length) index else -1
    }

    private fun getStartNotActualViewNameIndex(): Int {
        val valueIdInLowerCase = valueIdWithoutDefinition.toLowerCase()
        ALL_ANDROID_WIDGETS.forEach {
            val index = valueIdInLowerCase.lastIndexOf(it.toLowerCase())
            if (index != -1 && index + it.length == valueIdWithoutDefinition.length) {
                return index
            }
        }
        return valueIdWithoutDefinition.length
    }
}
