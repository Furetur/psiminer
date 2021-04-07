package javadoc

import astminer.common.model.Node

private const val TAG_NODE_TYPE = "DOC_TAG"

fun extractJavaDoc(node: Node): JavaDoc {
    return JavaDoc(extractDescription(node), extractTags(node))
}

private fun extractTags(node: Node): List<JavaDocTag> {
    return node.getChildrenOfType(TAG_NODE_TYPE).mapNotNull {
        try {
            extractTag(it)
        } catch (e: TagExtractionException) {
            println(e)
            null
        }
    }
}
