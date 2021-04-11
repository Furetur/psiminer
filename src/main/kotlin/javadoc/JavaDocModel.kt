package javadoc

import astminer.common.model.Node
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JavaDoc(val description: String, val tags: List<JavaDocTag>)

@Serializable
sealed class JavaDocTag {
    abstract val tagName: String
}

@Serializable
@SerialName("tagWithIdentifierAndInfo")
data class JavaDocIdentifierTag(override val tagName: String, val identifierToken: String, val data: String) :
    JavaDocTag()

@Serializable
@SerialName("simpleTag")
data class JavaDocSimpleTag(override val tagName: String, val data: String) : JavaDocTag()
