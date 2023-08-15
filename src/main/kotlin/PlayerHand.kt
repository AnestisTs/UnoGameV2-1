class PlayerHand(name: String){

    var hand : MutableList<Card> = mutableListOf<Card>()

    fun drawCard(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){


        if (deck.size >= 1) {
            hand.add(deck.elementAt(0))
            deck.removeAt(0)
        }else{
           addStackToDeck(deck, stack, currentCard)
        }

    }
    fun addStackToDeck(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){

        // *  Karte wird oben vom Stack runter genommen und zum stack hinzugegüht
        stack.removeAt(0)
        // * Mit addAll werden Listen zu Listen hinzugefügt (in meinem Fall das stack zum deck)
        deck.addAll(stack)
        // * hier wird der Stack gecleared(komplett entleert)
        stack.clear()                                       //
        // ! Card könnte auch null sein, deswegen muss hier gecheckt werden, ob es ungleich null ist.
        if (currentCard != null){
            stack.add(currentCard)
        }
    }

    fun drawTwo(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){

        if (deck.size < 2){
            addStackToDeck(deck, stack, currentCard)                                                                // * fügt das Stack zum Deck hinzu, wenn weniger als 2 Karten in dem Deck sind.
        }
        hand.addAll((currentCard as DrawTwoCard).drawTwo(deck))                        // ! IntelliJ hat mir das so als Lösung vorgeschlagen lol (Smartcast).
        println()

    }

    fun showPlayerHand(){
       // println("Hand des Spielers $currentPlayer")

        for ((index, card) in hand.withIndex()) {                                                                    // * index, card sind in klammern, weil es ein syntax fehler geben würde. Es beinhaltet 2 informationen.
            println("$index. $card")                                                                        // *  Anzeige auf der Konsole der Karten des momentan Spielers
        }
    }
}