package io.github.chase22.docparser.data

import io.github.chase22.docparser.snakeToCamelCase
import org.jsoup.nodes.Element

data class Field(val name: String, val type: String, val description: String) {
    val fieldName = snakeToCamelCase(name)
    val optional = description.startsWith("Optional.")

    companion object {
        fun create(row: Element): Field {
            val (name, type, description) = row.select("td").map { it.text() }
            return Field(name, type, description)
        }
    }
}
