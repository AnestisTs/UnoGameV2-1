// * FEHLER BEHOBEN. *EINGEBAUT*. Baue ich ein, sobald mein Spiel wieder läuft. Estimated 1-2 fehler behebung.
// * *GELÖST* Der Index zeigt immer die Farbe der vorherigen Karte an.
// * playerHands wird mit 2 mutableLists of deklariert, weil es eine Liste an Listen von den Spieler Händen ist.
// * FIXED wenn reverse, skip funktion falsch
// * FIXED drawtwo noch fehlerhaft
// * FIXED ab einem gewissen punkt. werden immer weiter "Karten gezogen"
// * FIXED Spielstart fehlerhaft. trotz nein eingabe geht es einen schritt weiter.
// * FIXED Mehr Printlines zur Information der Regeln.
// ! Feedback falls falsche Kartenlegung *evlt die Person nächste Runde überspringen* / INFO
// ? Kosmetische Verbesserung Wert. Vor KartenWert schreiben
// ? Kosmetische verbesserung. Kommentare über den Code schreiben
// ! Player klasse erstellen statt in der UnoGame.kt bsp name hand. evtl punkte Stand Mehr funktionen ( kampschrei )
// ! stats funktion
// ! user / KIuser erbt von player
// ! IF Abfrage für die Eingabe des Indexes der Maximalen Karten auf der Hand bei Zeile 96? Chosecard
// !! !!!! ab einem gewissen Punkt, wird in endlos schleife ein fehler ausgeworfen, der mich immer wieder karten ziehen lässt und alle runden überpsringt.
// ! evtl weil die letzte karte nicht mit der currentcard übereinstimmt und ich damit einen fehler trigger?
// ! deck & stack als klassen
// * FIXED reverse als erste karte führt nicht den geweünschten effekt aus
// ! skip als erste karte führt nicht den geweünschten effekt aus
// ! TODO NOW! npcplayer class erbend vom player erstellen mit spiellogic vom normalem game ! //
// ! ^statt readln mit if else arbeiten
// ! evlt karten nicht sichtbar machen
// ! TODO in near feature if enough time/after fulfilling project requierments. ! //
// ! TODO add class DECK&STACK for project improvement ! //
// ! TODO add NPC class for project improvement ! //


var currentCard: Card = Card(CardColor.BLUE, CardValue.FIVE)   // * Dummy wert, der direkt überschrieben wird                              // * Ist die gelegte Karte auf dem Stapel, die eine Aktion erfordert vom nächsten Spieler. ? = null fängt einen möglich crash ab für einen jetzt nicht existierenden Wert. // ? null wert eingebaut arbeite aber nicht mit 0 evlt try catch stattdessen benutzen?
var stack = mutableListOf<Card>()                           // * Stack ist der Kartenstapel, der gelegten Karten und wird mit Card befüllt
var gameOver: Boolean = false                                   // *  standartmäßiug falsch gesetzt, da dass Spiel fortlaufend ist
var currentPlayer: Int = 0                                                          // * standartmäßig auf 0 gesetzt um den Spieler zu initialisieren
var players : MutableList<Player> = mutableListOf()
val deck: MutableList<Card> = mutableListOf()                                                   // * variable fürs deck erstellt mit einer mutablelist der Klasse Card als Datentyp.

var clockwisePlayerTurns: Boolean = true


// * Die Inspiration hierfür, habe ich mir von Benni geholt.
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
            userInputPlayers = readln().toInt()                     // * Dummy Wert wird überschrieben
            incorrectUserInput = false

        } catch (e: Exception) {
            println("Das was du versuchst, macht keinen Sinn. Bitte versuche es erneut.")
            incorrectUserInput = true
        }

    } while (incorrectUserInput)

    return userInputPlayers
}


