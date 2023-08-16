open class Player(var name: String) {

    var playerHand: PlayerHand = PlayerHand(name)
    open fun battleCry(hand: PlayerHand) {

        if (playerHand.hand.size == 1) {
            println(" $name: ZACK, Uno letzte Karte.. Deal with it")
        }


    }

    // * sicherung zur korrekten eingabe des Indexes in eine Methode innerhalb der Player.kt verschoben. Lesbarer Code.
    open fun chooseCard(): Card {

        // * -1 dummy wert, damit die schleife das erste mal läuft, und der nutzer die chance hat, eine eingabe zu tätigen.
        var chosenCardIndex = -1
        // * size ist eine nummerierung die bei 1 starten, ich arbeite aber mit einem index.
        var maxCardIndex = playerHand.hand.size -1
        var wrongUserInput = false


        // * solange der chosencardindex NICHT im rahmen der erlaubten werte ist, läuft diese schleife
        while (!(chosenCardIndex in 0 .. maxCardIndex)) {
            if (wrongUserInput) {
                println("Du hast nur ${maxCardIndex} Karten auf der Hand, lern bitte zählen/lesen und versuchs erneut.")

            }
           try {
               chosenCardIndex = readln().toInt()

            // * wieder auf -1 gesetzt, damit man sichergeht, dass der nutzer aufjeden Fall nochmals die chance hat, den richtigen wert einzugeben *schleife nochmals starten*
           }catch (e: Exception){
                chosenCardIndex = -1

           }
            wrongUserInput = true
        }

        return playerHand.hand.elementAt(chosenCardIndex)
    }

}