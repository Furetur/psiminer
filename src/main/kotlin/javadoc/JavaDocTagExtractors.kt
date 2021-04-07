package javadoc

import astminer.common.model.Node

private const val TAG_NAME_NODE_TYPE = "DOC_TAG_NAME"
private const val TAG_VALUE_TOKEN_NODE_TYPE = "DOC_PARAMETER_REF|DOC_TAG_VALUE_TOKEN"

class TagExtractionException(reason: String, node: Node) :
    UnsupportedOperationException("Failed to extract tag from $node because $reason")

fun extractTag(node: Node): JavaDocTag {
    return when (extractTagName(node)) {
        ParamTagExtractor.tagName -> ParamTagExtractor.extractTag(node)
        SeeTagExtractor.tagName -> SeeTagExtractor.extractTag(node)
        else -> DefaultTagExtractor.extractTag(node)
    }
}

private fun extractTagName(node: Node): String {
    return node.getChildOfType(TAG_NAME_NODE_TYPE)?.getToken()?.drop(1)
        ?: throw TagExtractionException("Could not extract name", node)
}

interface JavaDocTagExtractor {
    fun extractTag(node: Node): JavaDocTag
}

object ParamTagExtractor : JavaDocTagExtractor {
    const val tagName = "param"

    override fun extractTag(node: Node): JavaDocTag {
        val identifierTag = node.getChildOfType(TAG_VALUE_TOKEN_NODE_TYPE)?.getToken() ?: error("Failed")
        val description = extractDescription(node)
        return JavaDocIdentifierTag(tagName, identifierTag, description)
    }
}

object SeeTagExtractor : JavaDocTagExtractor {
    const val tagName = "see"

    private const val REFERENCE_NODE_TYPE = "DOC_METHOD_OR_FIELD_REF"

    override fun extractTag(node: Node): JavaDocTag {
        val data = node.getChildOfType(REFERENCE_NODE_TYPE)?.getToken() ?: error("aa")
        return JavaDocSimpleTag(tagName, data)
    }
}

object DefaultTagExtractor : JavaDocTagExtractor {
    override fun extractTag(node: Node): JavaDocTag {
        val name = extractTagName(node)
        val tagBody = node.getToken()
        return JavaDocSimpleTag(name, tagBody)
    }
}