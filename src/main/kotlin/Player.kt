open class Player(var name: String) {

    var playerHand: PlayerHand = PlayerHand(name)
    open fun battleCry(hand: PlayerHand) {

        if (playerHand.hand.size == 1) {
            println(" $name: ZACK, Uno letzte Karte.. Deal with it")
        }


    }

    // * sicherung zur korrekten eingabe des Indexes in eine Methode innerhalb der Player.kt verschoben. Lesbarer Code.
    open fun chooseCard(): Card {

        var chosenCardIndex = -1
        var maxCardIndex = playerHand.hand.size
        var wrongUserInput = false

        // * Until< ist wie kleiner maxCardIndex, deswegen konnte ich die playerhand.hand.size nehmen, ohne -1 rechnen
        while (!(chosenCardIndex in 0 until maxCardIndex)) {
            if (wrongUserInput) {
                println("Du hast nur ${maxCardIndex} Karten auf der Hand, lern bitte zählen/lesen und versuchs erneut.")

            }
           try {
               chosenCardIndex = readln().toInt()

           }catch (e: Exception){
                chosenCardIndex = -1

           }
            wrongUserInput = true
        }

        return playerHand.hand.elementAt(chosenCardIndex)
    }

}