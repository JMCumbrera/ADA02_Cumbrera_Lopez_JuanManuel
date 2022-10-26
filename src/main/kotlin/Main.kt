import kotlin.system.exitProcess

fun main() {
    println("¡Bienvenido al menú de búsqueda de campeones!")
    println("=============================================\r\n")
    println("1. Buscar un personaje.")
    println("2. Salir de la aplicación.\r\n")
    print("Elija una opción: ")

    when(readln().toInt()) {
        1 -> {
            print("\r\nIntroduzca el nombre del personaje: ")
            val character = readln().toString().replaceFirstChar { it.uppercase() }
            val characterSearch = CharacterSearch()
            val characterSearchList = characterSearch.characterSearch(character)

            if (characterSearchList) {
                println("*** Personaje encontrado. Generando informe ***\r\n")
            } else {
                println("\r\nPersonaje no encontrado, ¿desea buscar otro personaje?\r\n")
                println("1. Sí")
                println("2. No, salir de la aplicación.\r\n")
                print("Elija una opción: ")

                when (readln().toInt()) {
                    1 -> {
                        var bool = false

                        do {
                            print("\r\nIntroduzca el nombre del personaje: ")
                            val pj = readln().toString().replaceFirstChar { it.uppercase() }
                            val characterSearchList2 = characterSearch.characterSearch(pj)
                            if (characterSearchList2) {
                                println("*** Personaje encontrado. Generando informe ***\r\n")
                                bool = true
                            } else {
                                println("\r\nPersonaje no encontrado\r\n")
                            }
                        } while (!bool)
                    }
                    2 -> { exitProcess(-1) }
                    else -> { throw IllegalArgumentException() }
                }
            }
        }
        2 -> { exitProcess(-1) }
        else -> { throw IllegalArgumentException() }
    }
}