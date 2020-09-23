package io.github.chase22.docparser.data

import org.jsoup.nodes.Element

data class Type(val titleElement: Element, val descriptionElement: Element, private val table: Element) {
    val title = titleElement.text()
    val description = descriptionElement.text()

    val fields = table.select("tr").drop(1).map(Field.Companion::create)

    override fun toString(): String {
        return "${title.padEnd(25, ' ')}${description}\t${fields}"
    }
}
