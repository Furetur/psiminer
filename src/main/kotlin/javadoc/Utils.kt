package javadoc

import astminer.common.model.Node

private const val DESCRIPTION_NODE_TYPE = "DOC_COMMENT_DATA"

internal fun extractDescription(node: Node): String = with(node) {
    return getChildrenOfType(DESCRIPTION_NODE_TYPE).joinToString("") { it.getToken() }.normalized()
}

internal fun String.normalized(): String =
    replace("\n", " ")
        .replace("\t", " ").replace("\\s\\s+".toRegex(), " ").trim()

/**
 * Escapes all not escaped double quotes.
 * Replaces all double quotes `"` with escaped double quotes `\"`,
 * but does not replace already escaped quotes `\"` with `\\"`.
 */

internal fun String.withEscapedDoubleQuotes(): String = replace(("""(?<!\\)"""").toRegex(), """\\"""")
