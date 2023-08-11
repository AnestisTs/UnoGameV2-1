import java.util.*

class UnoGame(var numberPlayers: Int){



    var currentCard: Card? = null                                // * Ist die gelegte Karte auf dem Stapel, die eine Aktion erfordert vom nächsten Spieler. ? = null fängt einen möglich crash ab für einen jetzt nicht existierenden Wert. // ? null wert eingebaut arbeite aber nicht mit 0 evlt try catch stattdessen benutzen?
    var stack = mutableListOf<Card>()                           // * Stack ist der Kartenstapel, der gelegten Karten und wird mit Card befüllt
    var gameOver: Boolean = false                                   // *  standartmäßiug falsch gesetzt, da dass Spiel fortlaufend ist
    var currentPlayer: Int = 0                                                          // * standartmäßig auf 0 gesetzt um den Spieler zu initialisieren
    var playerHands = mutableListOf<MutableList<Card>>()    //!  müsste aber hierdrauf zugreifen                               // * playerHands wird mit 2 mutableLists of deklariert, weil es eine Liste an Listen von den Spieler Händen ist.
    val deck: MutableList<Card> = mutableListOf()                                                   // * variable fürs deck erstellt mit einer mutablelist der Klasse Card als Datentyp.
    val playerHand = mutableListOf<Card>()  // ! draw2 wird hier hinzugefügt
    var clockwisePlayerTurns: Boolean = true


    init {                                                      // *  initialisierung vom Start des Kartenspiels


                                                      // * hier läuft die schleife für jede Farbe durch
                                                     // *  hier läuft die schleife für jeden Wert durch
                                                      // * und hier wird jede Karte mit Farbe und wert kombiniert und dem Deck als Card hinzugefügt.
        for (color in CardColor.entries) {
            for (value in CardValue.entries) {
                if (value == CardValue.SKIP) {
                    deck.add(SkipCard(color, value))

                } else if (value == CardValue.DRAWTWO) {
                    deck.add(DrawTwoCard(color, value))

                } else if (value == CardValue.REVERSE){
                    deck.add(ReverseCard(color, value))

                } else {
                    deck.add(Card(color, value))
                }
            }
        }


        Collections.shuffle(deck)                                // * collections ist auf der util Bibiliothek von Java enthalten und mit .shuffle greift man auf dies Methode der  Klasse zu und Mischt in meinem Fall das Deck
        for (i in 0 until numberPlayers) {                   // * schleife für kartenasugabe an anzahl der Spieler
            val hand = mutableListOf<Card>()                     // * variable für die Hand des Spielers erstellen. Hier wird Eine mutableListOf (Card) verwendet.
            for (j in 0 until 7) {                           // * schleife für Kartenausgabe an die Spieler (7 Karten)
                hand.add(deck.removeAt(0))                 // * der Spieler bekommt dann 7 karten aus dem Deck und jede Karte wird am Index 0 aus dem Deck entfernt.

            }
            playerHands.add(hand)                                // * die hand wird mit playerHands.add(hand) zur Liste playerHands hinzugefügt und somit Teil der Liste der Spielerhände.

        }

        val initialCard = deck.removeAt(0)                  // *  start Karte wird vom 0. Index bzw vom Anfang der "Liste" bzw des Decks entfernt
        stack.add(initialCard)                                       // * start Karte zum Anfang des Spiels kommt auf den Stapel(Stack)
        currentCard = initialCard                                    // * hier ist ein Fehler der immer wieder die initial card statt die currentcard anzeigen lässt // GELÖST // initialcard steht für die initialisierungsKarte des Spiels, quasi zum Anfang hin
        println("Start Karte $initialCard")
    }

    fun drawCard(){

        if (deck.size <= 1) {
            playerHand.add(deck.elementAt(0))
            deck.removeAt(0)
        }else{
            addStackToDeck()

        }



    }

    fun addStackToDeck(){

        stack.removeAt(0)
        deck.addAll(stack)                                              // * Mit addAll werden Listen zu Listen hinzugefügt
        stack = mutableListOf()
        if (currentCard != null){                                       // ! Card könnte auch null sein, deswegen muss hier gecheckt werden, ob es ungleich null ist.
            stack.add(currentCard!!)                                    // ! *currentCard!!* Smartcast vorschlag von IntelliJ
        }

    }

    fun getNextPlayerIndex(): Int {
        if(clockwisePlayerTurns) {
            if(currentPlayer < (numberPlayers -1)) {
                return currentPlayer + 1
            } else {
                return 0
            }
        } else {
            if(currentPlayer > 0) {
                return currentPlayer - 1
            } else {
                return numberPlayers - 1
            }
        }
    }

    fun startGame() {                                                                                        // * Eigentliche Beginn des Spiels

        while (!gameOver) {
            println("$currentPlayer ist jetzt am Zug.")                                                     // * Namensausgabe des ersten Spielers
            println("$currentCard")
            if (currentCard is DrawTwoCard){                                                        //
                if (deck.size < 2){
                    addStackToDeck()                                                                // * fügt dass den Stack zum Deck hinzu, wenn weniger als 2 Karten in dem Deck sind.
                }
                playerHand.addAll((currentCard as DrawTwoCard).drawTwo(deck))                        // ! IntelliJ hat mir das so als Lösung vorgeschlagen lol (Smartcast). Fehlerhaft. Karten werden vom Deck entfernt, aber nicht auf die Hand hinzugefügt
                println()                                                                             // ! Karten werden
            }


            val  playerHandWithIndex = playerHands.get(currentPlayer).withIndex()                                         // *  mit playerhands.get(currentplayer) wird das deck des momentanen spielers angezeigt. .withindex fügt die information des Index hinzu.

            for ((index, card) in playerHandWithIndex) {                                                                  // * index, card sind in klammern, weil es ein syntax fehler geben würde. Es beinhaltet 2 informationen.
                println("$index. $card")                                                                        // *  Anzeige auf der Konsole der Karten des momentan Spielers
            }

            val playerHand = playerHands.get(currentPlayer)

            var isPlayable = playerHand.any { card -> currentCard?.color == card.color || currentCard?.value == card.value }   // * lambda benutzt, um eine Legbare Karte aus der Spielerhand zu finden
            if (isPlayable) {
                val chosenCardIndex = readln().toInt()                                                                  // * Kartenauswhal vom Spieler seiner Hand über Konsole
                var chosenCard = playerHand.elementAt(chosenCardIndex)                                                  // ? Ausgewählte Karte vom Spieler aus seiner Hand über Index
                if (chosenCard.color == currentCard?.color || chosenCard.value == currentCard?.value) {
                    if (chosenCard is SkipCard){                                                                // ! IS durch chatgpt erlernt. Hätte ich das nicht gemacht
                        currentPlayer = chosenCard.skipPlayer(clockwisePlayerTurns, currentPlayer, numberPlayers)                     // ! könnte ich skipPlayer nicht benutzen
                    }
                    if (chosenCard is ReverseCard){
                        clockwisePlayerTurns = chosenCard.reverseIt(clockwisePlayerTurns)
                    }
                    stack.add(chosenCard)
                    currentCard = chosenCard                                                                                // * currengcard als chosencard deklariert um die aktuelle karte auf dem stapel aufzuzeigen
                    playerHand.remove(chosenCard)                                                    // * Karte wird zum Stapel hinzugefügt und aus der Spielerhand entnommen
                }
            } else {
                println("bitte ziehe eine Karte")
                drawCard()

            }

            currentPlayer = getNextPlayerIndex()
            if (playerHand.size == 0){
                gameOver = true
                println("Spieler $currentPlayer hat gewonnen. Herzlichen glückwunsch, du bist ein noob.")

            }
            println("################################")
        }


    }


}

