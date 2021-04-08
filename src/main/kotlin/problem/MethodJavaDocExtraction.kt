package problem

import astminer.common.model.Node
import javadoc.extractJavaDoc
import javadoc.withEscapedDoubleQuotes
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import psi.PsiNode

class MethodJavaDocExtraction : LabelExtractor {

    companion object {
        const val name = "method javadoc extraction"
    }

    override val granularityLevel = GranularityLevel.Method

    override fun processTree(root: PsiNode): Sample? {
        val javaDocNode = root.getJavaDoc() ?: return null
        val javaDoc = extractJavaDoc(javaDocNode)
        val label = Json.encodeToString(javaDoc)
        root.removeJavaDoc()
        return Sample(root, label.withEscapedDoubleQuotes())
    }


    private fun PsiNode.getJavaDoc(): Node? = getChildOfType("DOC_COMMENT")

    private fun PsiNode.removeJavaDoc() {
        getChildOfType("MODIFIER_LIST")?.removeChildrenOfType("DOC_COMMENT")
    }
}