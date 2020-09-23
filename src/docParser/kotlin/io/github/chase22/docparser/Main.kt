package io.github.chase22.docparser

import io.github.chase22.docparser.data.Type
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


fun main() {
    val doc: Document = Jsoup.connect("https://core.telegram.org/bots/api").get()
    println(doc.title())
    val siblings = doc.select("h3:contains(Available Types)").first().nextElementSiblings()

    val titles = Elements()

    for (sibling in siblings) {
        if (sibling.tagName() == "h3") break
        if (sibling.tagName() == "h4" && listOf("InputFile", "Sending files", "Inline mode objects").contains(sibling.text())) continue
        titles.add(sibling)
    }

    val types = titles.select("h4")
            .map {

                val description = it.nextElementSiblings().select("p").first()
                val table = it.nextElementSiblings().select("table").first()

                Type(it, description, table)
            }
    println(convertToClassFile(types.first()).absolutePath)
}
