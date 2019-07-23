package effective.com.lib.utils


fun String.removeAfterCharacter(character: Char) =
    if (indexOf(character) > 0) substring(0, lastIndexOf(character)) else this

fun String.removeBeforeCharacter(character: Char) =
    if (indexOf(character) > 0) substring(lastIndexOf(character) + 1, length) else this

fun String.toCamelCase(capitalizeFirstLetter: Boolean, vararg delimiters: Char): String {
    var sourceString = this
    if (sourceString.isBlank()) {
        return ""
    }

    sourceString = sourceString.toLowerCase()
    val newCodePoints = IntArray(sourceString.length)
    val delimiterSet = generateDelimiterSet(delimiters)
    var capitalizeNext = false
    var outOffset = 0
    if (capitalizeFirstLetter) {
        capitalizeNext = true
    }

    var index = 0
    while (index < sourceString.length) {
        val codePoint = sourceString.codePointAt(index)

        if (delimiterSet.contains(codePoint)) {
            capitalizeNext = true
            if (outOffset == 0) {
                capitalizeNext = false
            }
            index += Character.charCount(codePoint)
        } else {
            if (capitalizeNext || outOffset == 0 && capitalizeFirstLetter) {
                val titleCaseCodePoint = Character.toTitleCase(codePoint)
                newCodePoints[outOffset++] = titleCaseCodePoint
                index += Character.charCount(titleCaseCodePoint)
                capitalizeNext = false
            } else {
                newCodePoints[outOffset++] = codePoint
                index += Character.charCount(codePoint)
            }
        }
    }

    return if (outOffset != 0) String(newCodePoints, 0, outOffset) else sourceString
}

private fun generateDelimiterSet(delimiters: CharArray?): Set<Int> {
    val delimiterHashSet = HashSet<Int>()
    delimiterHashSet.add(getDefaultDelimiterCode())

    if (delimiters == null || delimiters.isEmpty()) {
        return delimiterHashSet
    }

    delimiters.forEachIndexed { index, _ ->
        delimiterHashSet.add(Character.codePointAt(delimiters, index))
    }

    return delimiterHashSet
}

private fun getDefaultDelimiterCode() = Character.codePointAt(charArrayOf(' '), 0)
