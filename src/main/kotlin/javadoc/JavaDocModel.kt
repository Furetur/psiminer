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

fun printNode(node: Node, level: Int = 0) {
    println("${"\t".repeat(level)}Node ${node.getTypeLabel()} ::: ${node.getToken()}")
    println("${"\t".repeat(level)}Children:")
    for ((index, child) in node.getChildren().withIndex()) {
        println("${"\t".repeat(level + 1)} ${index + 1})")
        printNode(child, level + 1)
    }
}
