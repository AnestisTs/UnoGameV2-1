// * Die Inspiration hierfür, habe ich mir von Benni geholt.
// * FARBE DER KARTEN *
var green = "\u001B[32m"
var red = "\u001B[31m"
var blue = "\u001B[34m"
var yellow = "\u001B[33m"
var magenta = "\u001B[35m"
var reset = "\u001B[0m"
// * FARBE DER KARTEN *

fun getNumPlayers(npc: Boolean = false, maxPlayers: Int): Int {

    var incorrectUserInput: Boolean = false
    // * Dummy Wert
    var userInputPlayers = 2
    var player = ""

    if (npc){
        player = "Bots"

    }else{
        player = "Spielern"
    }
    do {
        try {
            println("Bitte gib die Anzahl an $player an (maximal $maxPlayers)")
            // * Dummy Wert wird hier mit usereingabe überschrieben
            userInputPlayers = readln().toInt()
            incorrectUserInput = false

        } catch (e: Exception) {
            println("Das was du versuchst, macht keinen Sinn. Bitte versuche es erneut.")
            incorrectUserInput = true
        }

    } while (incorrectUserInput)

    return userInputPlayers
}


