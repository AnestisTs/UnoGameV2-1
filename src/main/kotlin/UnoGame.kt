import java.util.*

class UnoGame(var numberPlayers: Int, var playerNames: MutableList<String>){



    var currentCard: Card = Card(CardColor.BLUE, CardValue.FIVE)   // * Dummy wert, der direkt überschrieben wird                              // * Ist die gelegte Karte auf dem Stapel, die eine Aktion erfordert vom nächsten Spieler. ? = null fängt einen möglich crash ab für einen jetzt nicht existierenden Wert. // ? null wert eingebaut arbeite aber nicht mit 0 evlt try catch stattdessen benutzen?
    var stack = mutableListOf<Card>()                           // * Stack ist der Kartenstapel, der gelegten Karten und wird mit Card befüllt
    var gameOver: Boolean = false                                   // *  standartmäßiug falsch gesetzt, da dass Spiel fortlaufend ist
    var currentPlayer: Int = 0                                                          // * standartmäßig auf 0 gesetzt um den Spieler zu initialisieren
    var players : MutableList<Player> = mutableListOf()
    val deck: MutableList<Card> = mutableListOf()                                                   // * variable fürs deck erstellt mit einer mutablelist der Klasse Card als Datentyp.

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
            val player = Player(playerNames.removeAt(0))    // * Der Player braucht immer ein Namen. Jeder Spieler hat einen Namen hierdurch und wird nicht doppelt angezeigt                  // * variable für die Hand des Spielers erstellen. Hier wird Eine mutableListOf (Card) verwendet.
            for (j in 0 until 7) {                           // * schleife für Kartenausgabe an die Spieler (7 Karten)
                player.playerHand.hand.add(deck.removeAt(0))                 // * der Spieler bekommt dann 7 karten aus dem Deck und jede Karte wird am Index 0 aus dem Deck entfernt.

            }
            players.add(player)                                // * die hand wird mit playerHands.add(hand) zur Liste playerHands hinzugefügt und somit Teil der Liste der Spielerhände.

        }

        val initialCard = deck.removeAt(0)                  // *  start Karte wird vom 0. Index bzw vom Anfang der "Liste" bzw des Decks entfernt
        stack.add(initialCard)                                       // * start Karte zum Anfang des Spiels kommt auf den Stapel(Stack)
        currentCard = initialCard                                    // * hier ist ein Fehler der immer wieder die initial card statt die currentcard anzeigen lässt // GELÖST // initialcard steht für die initialisierungsKarte des Spiels, quasi zum Anfang hin
        println("Start Karte $initialCard")
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
            val player = players.get(currentPlayer)
            println("${player.name} ist jetzt am Zug.")                                                     // * Namensausgabe des ersten Spielers
            println("$currentCard")
            if (currentCard is DrawTwoCard){                                                        //
               player.playerHand.drawTwo(deck, stack, currentCard)
            } else if (currentCard is ReverseCard){
                clockwisePlayerTurns = false
            }else if (currentCard is SkipCard){
                currentPlayer = (currentCard as SkipCard).skipPlayer(clockwisePlayerTurns, currentPlayer, numberPlayers)
            }


            val  playerHandWithIndex = player.playerHand.hand.withIndex()                                         // *  mit playerhands.get(currentplayer) wird das deck des momentanen spielers angezeigt. .withindex fügt die information des Index hinzu.

            for ((index, card) in playerHandWithIndex) {                                                                        // * index, card sind in klammern, weil es ein syntax fehler geben würde. Es beinhaltet 2 informationen.
                println("$index. $card")                                                                        // *  Anzeige auf der Konsole der Karten des momentan Spielers
            }


            var playerHand = player.playerHand
            var isPlayable = playerHand.hand.any { card -> currentCard.color == card.color || currentCard.value == card.value }   // * lambda benutzt, um eine Legbare Karte aus der Spielerhand zu finden
            if (isPlayable) {
                val chosenCardIndex = readln().toInt()                                                                  // * Kartenauswhal vom Spieler seiner Hand über Konsole
                if (chosenCardIndex <= playerHand.hand.size - 1){

                }else{                                                                      // * sicherung zur korrekten eingabe des Indexes
                    println("Du hast nur ${playerHand.hand.size-1} Karten auf der hand")
                    return startGame()
                }
                var chosenCard = playerHand.hand.elementAt(chosenCardIndex)                                                  // ? Ausgewählte Karte vom Spieler aus seiner Hand über Index
                if (chosenCard.color == currentCard.color || chosenCard.value == currentCard.value) {
                    if (chosenCard is SkipCard){                                                                        // ! ELSE IF einbauen    // ! IS durch chatgpt erlernt. Hätte ich das nicht gemacht
                        currentPlayer = chosenCard.skipPlayer(clockwisePlayerTurns, currentPlayer, numberPlayers)                     // ! könnte ich skipPlayer nicht benutzen
                    }
                    else if (chosenCard is ReverseCard){                                                             // * Baue damit ein interface auf. Es werden die eigenschaften übernommen
                        clockwisePlayerTurns = chosenCard.reverseIt(clockwisePlayerTurns)
                    }
                    stack.add(chosenCard)
                    currentCard = chosenCard                                                                                // * currengcard als chosencard deklariert um die aktuelle karte auf dem stapel aufzuzeigen
                    playerHand.hand.remove(chosenCard)                // * Karte wird zum Stapel hinzugefügt und aus der Spielerhand entnommen
                }
            } else {
                println("bitte ziehe eine Karte")
                playerHand.drawCard(deck, stack, currentCard)

            }

            currentPlayer = getNextPlayerIndex()
            if (playerHand.hand.size == 0){
                gameOver = true                         // ! Spiel wieder von neu anfangen, karten müssen neu ausgeteilt werden
                println("Spieler $currentPlayer hat gewonnen. Herzlichen glückwunsch, du bist ein noob.")

            }
            println("################################")
        }


    }


}

