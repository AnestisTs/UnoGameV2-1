class PlayerHand(name: String){

    var hand : MutableList<Card> = mutableListOf<Card>()

    fun drawCard(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){


        if (deck.size < 1) {
            addStackToDeck(deck, stack, currentCard)

        }
        hand.add(deck.elementAt(0))
        deck.removeAt(0)
    }
    fun addStackToDeck(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){

        // *  Karte wird oben vom Stack runter genommen.
        stack.removeAt(0)
        // * Mit addAll werden Listen zu Listen hinzugefügt (in meinem Fall das stack zum deck)
        deck.addAll(stack)
        // * hier wird der Stack gecleared(komplett entleert)
        stack.clear()                                       //
        // * null check nicht mehr gegeben, da die Card nicht mehr 0 sein kann. Die CurrentCard wird zum Stack hinzugefügt.
        stack.add(currentCard)
    }

    fun drawTwo(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){

        // * fügt das Stack zum Deck hinzu, wenn weniger als 2 Karten in dem Deck sind.
        if (deck.size < 2){
            addStackToDeck(deck, stack, currentCard)
        }
        // *
        // ! IntelliJ hat mir das so als Lösung vorgeschlagen lol (Smartcast).
        hand.addAll((currentCard as DrawTwoCard).drawTwo(deck))
        println()

    }

    fun showPlayerHand(){
       // println("Hand des Spielers $currentPlayer")
        // * index, card sind in klammern, weil es ein syntax fehler geben würde. Es beinhaltet 2 informationen.
        for ((index, card) in hand.withIndex()) {
            // *  Anzeige auf der Konsole der Karten des momentan Spielers
            println("$index. $card")
        }
    }
}