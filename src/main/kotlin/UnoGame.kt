import java.util.*

class UnoGame(var numberPlayers: Int, var playerNames: MutableList<String>){

    // * Dummy wert, der direkt überschrieben wird. // * Ist die gelegte Karte auf dem Stapel, die eine Aktion erfordert vom nächsten Spieler.
    var currentCard: Card = Card(CardColor.BLUE, CardValue.FIVE)
    // * Stack ist der Kartenstapel, der gelegten Karten und wird mit Card befüllt
    var stack = mutableListOf<Card>()
    // *  standartmäßiug falsch gesetzt, da dass Spiel fortlaufend ist
    var gameOver: Boolean = false
    // * standartmäßig auf 0 gesetzt um den Spieler zu initialisieren
    var currentPlayer: Int = 0
    var players : MutableList<Player> = mutableListOf()
    // * variable fürs deck erstellt mit einer mutablelist der Klasse Card als Datentyp.
    val deck: MutableList<Card> = mutableListOf()
    var firstRound = true
    var clockwisePlayerTurns: Boolean = false  // ! testlauf, dran denken wieder true
    var disableDrawTwoCard = false

    // *  initialisierung vom Start des Kartenspiels
    init {

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
            var player = Player("")
            // * wenn der Spielername Npc enthält, wird ein Npc spieler erstellt.
            if (playerNames.first().contains("Npc")){
                player = NpcPlayer(playerNames.removeAt(0))

            }else{
                player = Player(playerNames.removeAt(0))
            }
            // * schleife für Kartenausgabe an die Spieler (7 Karten)
            for (j in 0 until 7) {
                // * der Spieler bekommt dann 7 karten aus dem Deck und jede Karte wird am Index 0 aus dem Deck entfernt.
                player.playerHand.hand.add(deck.removeAt(0))

            }
            // * die Spieler werden mit players.add(player) zur Liste players hinzugefügt und somit Teil der Liste der Spielerhände.
            players.add(player)

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
            // * firstround muss zu erst sein, da es einfluss auf den ersten spieler bzw der ersten runde hat.
           if (firstRound) {
               // * ist die erste Karte eine ReverseCard, wird die Spielrichtung direkt zu Anfang geändert und es geht nach dem ersten zum letzten Spieler

               if (currentCard is ReverseCard) {
                   clockwisePlayerTurns = !clockwisePlayerTurns
               } else if (currentCard is SkipCard) {
                   currentPlayer = getNextPlayerIndex()
               }
           }
            firstRound = false
            val player = players.get(currentPlayer)
            // * Namensausgabe des ersten Spielers
            println("${player.name} ist jetzt am Zug.")
            println("$currentCard")

            // * muss ausgeführt werden, nachdem wir uns den Spieler geholt haben, damit es auch den richtigen Spieler trifft.
            // * hier ist die currentCard noch die initialcard(anfangskarte). wenn diese zu beginn eine drawtwo ist, werden 2 karten der Spielerhand hinzugefügt
            if (currentCard is DrawTwoCard && !disableDrawTwoCard) {
                player.playerHand.drawTwo(deck, stack, currentCard)
            }
            // * muss wieder auf false gesetzt werden sonst funktioniert die drawtwo funktion nicht mehr
            disableDrawTwoCard = false
            // *  mit playerhands.get(currentplayer) wird das deck des momentanen spielers angezeigt. .withindex fügt die information des Index hinzu.

            player.playerHand.showPlayerHand()

            var playerHand = player.playerHand
            // * lambda benutzt, um eine Legbare Karte aus der Spielerhand zu finden
            var isPlayable = playerHand.hand.any { card -> currentCard.color == card.color || currentCard.value == card.value }
            if (isPlayable) {
                var chosenCard = Card(CardColor.BLUE, CardValue.FIVE)
                if (player is NpcPlayer){
                    chosenCard = player.chooseCardNpc(currentCard)
                }else{
                    // * Kartenauswhal vom Spieler seiner Hand über Konsole // * Ausgewählte Karte vom Spieler aus seiner Hand über Index
                         chosenCard = player.chooseCard()
                }

                if (chosenCard.color == currentCard.color || chosenCard.value == currentCard.value) {
                   // * chosencard wird hier als Skipcard deklariert. durch "is" werden die eigenschaften übernommen. // * Baue damit ein interface auf.
                    // ! IS durch chatgpt erlernt. Hätte ich das nicht gemacht, könnte ich skipPlayer nicht benutzen
                    if (chosenCard is SkipCard){
                         // * überspringt den Spieler, auf den die Karte gelegt wurde.
                        currentPlayer = chosenCard.skipPlayer(clockwisePlayerTurns, currentPlayer, numberPlayers)
                    }
                    else if (chosenCard is ReverseCard){
                        clockwisePlayerTurns = chosenCard.reverseIt(clockwisePlayerTurns)
                    }
                   // * hier wird die vom user ausgewählte karte zum Kartenstapel (Stack) hinzugefügt.
                    stack.add(chosenCard)
                    // * currengcard als chosencard deklariert um die aktuelle karte auf dem stapel aufzuzeigen
                    currentCard = chosenCard
                    // * die vom user ausgewählte karte wird aus der Spielerhand entfernt.
                    playerHand.hand.remove(chosenCard)
                }
            } else {
                println("bitte ziehe eine Karte")
                // * wenn die bedingungen von isPlayable nicht erfüllt werden, wird der Spieler gebeten eine Karte zu ziehen und tut dies.
                // *  Die funktion hierfür ist in PlayerHand.kt >>>
                disableDrawTwoCard = true
                playerHand.drawCard(deck, stack, currentCard)
            }

            // * Spielrichtung im Uhrzeiger SInn
            currentPlayer = getNextPlayerIndex()
            // * Ansage "Uno letzte Karte..." wenn Karten der Spieler Hand auf 1 sind. Player.kt>>
            player.battleCry(PlayerHand(player.name))

            // * wenn die Karten auf der Hand leer sind, wird das Spiel beendet.
            if (playerHand.hand.size == 0){
                gameOver = true
                // * Der Gewinner wird hier angezeigt.
                println("Spieler ${player.name} hat gewonnen. Herzlichen glückwunsch, du bist ein noob.")
            }
            println("################################")
        }

    }

}


