package io.github.chase22.docparser

import io.github.chase22.docparser.data.Field
import io.github.chase22.docparser.data.Type
import java.io.File

fun convertToClassFile(type: Type, parent: File): File {
    val file = File(parent, "${type.title}.kt")

    val sb = StringBuilder()

    sb.appendln("import com.fasterxml.jackson.annotation.JsonProperty")
    sb.appendln()

    sb.appendln("data class ${type.title} (")
    sb.appendln(type.fields.map(::convertToParameter).joinToString("\n").dropLast(2))
    sb.appendln(")")

    file.writeText(sb.toString())
    return file
}

fun convertToParameter(field: Field): String {
    val annotation = "@field:JsonProperty(\"${field.name}\")"
    val fieldDeclaration = "private val ${field.fieldName}"

    val optional = if (field.optional) "?" else ""
    val type = mapType(field.type) + optional

    return "\t$annotation $fieldDeclaration: $type,"
}

fun snakeToCamelCase(input: String): String {
    return input.split('_').joinToString(separator = "", transform = String::capitalize).decapitalize()
}

val typeMappings = mapOf(
        Pair("Integer", "Int")
)

fun mapType(type: String): String = typeMappings[type] ?: type
