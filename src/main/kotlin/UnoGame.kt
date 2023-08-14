import java.util.*

class UnoGame(var numberPlayers: Int, var playerNames: MutableList<String>){



    init {                                                      // *  initialisierung vom Start des Kartenspiels


        // * hier läuft die schleife für jede Farbe durch
        for (color in CardColor.entries) {
            // *  hier läuft die schleife für jeden Wert durch
            for (value in CardValue.entries) {
                // * skip Karte wird zum deck hinzugefügt
                if (value == CardValue.SKIP) {
                    deck.add(SkipCard(color, value))
               // * drawtwo Karte wird zum deck hinzugefügt
                } else if (value == CardValue.DRAWTWO) {
                    deck.add(DrawTwoCard(color, value))
                // * reverse Karte wird zum Deck hinzugefügt
                } else if (value == CardValue.REVERSE){
                    deck.add(ReverseCard(color, value))
                    // * und hier wird jede Karte mit Farbe und wert kombiniert und dem Deck als Card hinzugefügt.
                } else {
                    deck.add(Card(color, value))
                }
            }
        }

// * hier wird das deck geshuffled.
        deck.shuffle()
        // * schleife für kartenasugabe an anzahl der Spieler
        for (i in 0 until numberPlayers) {
            // * Der Player braucht immer ein Namen. Jeder Spieler hat einen Namen hierdurch und wird nicht doppelt angezeigt
            val player = Player(playerNames.removeAt(0))
            // * schleife für Kartenausgabe an die Spieler (7 Karten)
            for (j in 0 until 7) {
                // * der Spieler bekommt dann 7 karten aus dem Deck und jede Karte wird am Index 0 aus dem Deck entfernt.
                player.playerHand.hand.add(deck.removeAt(0))

            }
            players.add(player)                                // * die Spieler werden mit players.add(player) zur Liste players hinzugefügt und somit Teil der Liste der Spielerhände.

        }

        // *  start Karte wird vom 0. Index bzw vom Anfang der "Liste" bzw des Decks entfernt
        val initialCard = deck.removeAt(0)
        // * start Karte zum Anfang des Spiels kommt auf den Stapel(Stack)
        stack.add(initialCard)
        // * initialcard steht für die initialisierungsKarte des Spiels, quasi zum Anfang hin
        currentCard = initialCard                                    // * hier ist ein Fehler der immer wieder die initial card statt die currentcard anzeigen lässt // GELÖST
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
    // * Eigentliche Beginn des Spiels
    fun startGame() {

        while (!gameOver) {
            val player = players.get(currentPlayer)
            // * Namensausgabe des ersten Spielers
            println("${player.name} ist jetzt am Zug.")
            println("$currentCard")
            if (currentCard is DrawTwoCard){
               player.playerHand.drawTwo(deck, stack, currentCard)
            } else if (currentCard is ReverseCard){
                clockwisePlayerTurns = false
            }else if (currentCard is SkipCard){
                currentPlayer = getNextPlayerIndex()                        // ! passt noch nicht ganz, da hier der zweite spieler übersprungen wird...
            }

            // *  mit playerhands.get(currentplayer) wird das deck des momentanen spielers angezeigt. .withindex fügt die information des Index hinzu.
            val  playerHandWithIndex = player.playerHand.hand.withIndex()

            player.playerHand.showPlayerHand()


            var playerHand = player.playerHand
            // * lambda benutzt, um eine Legbare Karte aus der Spielerhand zu finden
            var isPlayable = playerHand.hand.any { card -> currentCard.color == card.color || currentCard.value == card.value }
            if (isPlayable) {
                // * Kartenauswhal vom Spieler seiner Hand über Konsole
                val chosenCardIndex = readln().toInt()
                // * sicherung zur korrekten eingabe des Indexes
                if (chosenCardIndex <= playerHand.hand.size - 1){

                }else{
                    println("Du hast nur ${playerHand.hand.size-1} Karten auf der Hand, mach eine vernünftige Eingabe.")
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
                println("Spieler ${player.name} hat gewonnen. Herzlichen glückwunsch, du bist ein noob.")

            }
            println("################################")
        }


    }


}

