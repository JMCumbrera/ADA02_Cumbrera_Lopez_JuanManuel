import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import java.io.PrintWriter
import javax.xml.parsers.DocumentBuilderFactory

class CharacterSearch {
    private val xmlFile: File = File(System.getProperty("user.dir") + System.getProperty("file.separator") + "personajes" + System.getProperty("file.separator") + "personajesLol.xml")
    private val xmlDOMFile:Document =DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile)

    private fun normalize() { xmlDOMFile.documentElement.normalize() }

    fun characterSearch(characterName: String): Boolean {
        normalize()
        val characters: NodeList = xmlDOMFile.getElementsByTagName(characterName)
        var characterPropertiesList: MutableList<String> = mutableListOf()

        for (i in 0 until characters.length) {
            if (characters.item(i).nodeType == Node.ELEMENT_NODE) {
                var character: Element = characters.item(i) as Element
                var characterProperties:NodeList = character.childNodes

                for (j in 0 until characterProperties.length) {
                    if (characterProperties.item(j).nodeType == Node.ELEMENT_NODE) {
                        var property: Element = characterProperties.item(j) as Element

                        if (property.tagName == "name") { characterPropertiesList.add("${property.tagName}: ${property.textContent}") }
                        if (property.tagName == "title") { characterPropertiesList.add("${property.tagName}: ${property.textContent}") }
                        if (property.tagName == "blurb") { characterPropertiesList.add("${property.tagName}: ${property.textContent}") }
                        if (property.tagName == "tags" && !(characterPropertiesList.last().startsWith("tags"))) { characterPropertiesList.add("${property.tagName}: ${property.textContent}") }
                    }
                }
            }
        }

        if (characterPropertiesList.isEmpty()) {
            return false
        } else {
            return createDocument(characterName, characterPropertiesList)
        }
    }

    private fun createDocument(characterName: String, characterPropertiesList: MutableList<String>): Boolean {
        val writeFile: File = File(System.getProperty("user.dir") + System.getProperty("file.separator") + "personajes" + System.getProperty("file.separator") + "$characterName.txt")
        writeFile.createNewFile()
        val pw: PrintWriter = PrintWriter(writeFile, Charsets.UTF_8)

        characterPropertiesList.forEach {
            pw.write("$it\r\n")
        }
        pw.close()
        return true
    }
}