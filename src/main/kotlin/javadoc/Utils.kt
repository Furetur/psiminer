package javadoc

import astminer.common.model.Node

private const val DESCRIPTION_NODE_TYPE = "DOC_COMMENT_DATA"

internal fun extractDescription(node: Node): String {
    return node.extractManyByNormalizing(DESCRIPTION_NODE_TYPE)
}

internal fun Node.extractManyByNormalizing(type: String): String {
    return getChildrenOfType(type).joinToString("") { it.getToken() }.normalized()
}

internal fun String.normalized(): String =
    replace("\n", " ")
        .replace("\t", " ").replace("\\s\\s+".toRegex(), " ").trim()
