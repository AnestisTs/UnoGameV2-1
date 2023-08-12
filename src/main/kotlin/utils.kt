// * Die Inspiration hierfür, habe ich mir von Benni geholt.
// * FEHLER BEHOBEN. *EINGEBAUT*. Baue ich ein, sobald mein Spiel wieder läuft. Estimated 1-2 fehler behebung.
// * *GELÖST* Der Index zeigt immer die Farbe der vorherigen Karte an.
// * playerHands wird mit 2 mutableLists of deklariert, weil es eine Liste an Listen von den Spieler Händen ist.
// ! wenn reverse, skip funktion falsch
// ! drawtwo noch fehlerhaft
// ! ab einem gewissen punkt. werden immer weiter "Karten gezogen"
// ! Spielstart fehlerhaft. trotz nein eingabe geht es einen schritt weiter.
// ! Mehr Printlines zur Information der Regeln.
// ! Feedback falls falsche Kartenlegung *evlt die Person nächste Runde überspringen* / INFO
// ? Kosmetische Verbesserung Wert. Vor KartenWert schreiben
// ? Kosmetische verbesserung. Kommentare über den Code schreiben
// ! Player klasse erstellen statt in der UnoGame.kt bsp name hand. evtl punkte Stand Mehr funktionen ( kampschrei )
// ! stats funktion
// ! user / KIuser erbt von player

// * FARBE DER KARTEN *

var green = "\u001B[32m"
var red = "\u001B[31m"
var blue = "\u001B[34m"
var yellow = "\u001B[33m"
var magenta = "\u001B[35m"
var reset = "\u001B[0m"

// * FARBE DER KARTEN *


fun getNumPlayers(): Int {

    var incorrectUserInput: Boolean = false
    var userInputPlayers = 2                                    // * Dummy Wert
    do {
        try {
            println("Bitte gib die Anzahl an Spielern an (2-4)")
            userInputPlayers = readln().toInt()                     // * Dummy wird überschrieben
            incorrectUserInput = false

        } catch (e: Exception) {
            println("Das was du versuchst, macht keinen Sinn. Bitte versuche es erneut.")
            incorrectUserInput = true
        }

    } while (incorrectUserInput)

    return userInputPlayers
}


