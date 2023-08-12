class PlayerHand(name: String){

    var hand : MutableList<Card> = mutableListOf<Card>()

    fun drawCard(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){


        if (deck.size <= 1) {
            hand.add(deck.elementAt(0))
            deck.removeAt(0)
        }else{
           addStackToDeck(deck, stack, currentCard)

        }

    }
    fun addStackToDeck(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){

        stack.removeAt(0)                                   // *  Karte wird runter genommen und zum stack hinzugegüht
        deck.addAll(stack)                                              // * Mit addAll werden Listen zu Listen hinzugefügt
        stack.clear()
        if (currentCard != null){                                       // ! Card könnte auch null sein, deswegen muss hier gecheckt werden, ob es ungleich null ist.
            stack.add(currentCard!!)                                    // ! *currentCard!!* Smartcast vorschlag von IntelliJ
        }
    }

    fun drawTwo(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){

        if (deck.size < 2){
            addStackToDeck(deck, stack, currentCard)                                                                // * fügt dass den Stack zum Deck hinzu, wenn weniger als 2 Karten in dem Deck sind.
        }
        hand.addAll((currentCard as DrawTwoCard).drawTwo(deck))                        // ! IntelliJ hat mir das so als Lösung vorgeschlagen lol (Smartcast). Fehlerhaft. Karten werden vom Deck entfernt, aber nicht auf die Hand hinzugefügt
        println()                                                                             // ! Karten werden

    }

    fun showPlayerHand(){
        println("Hand des Spielers ")

        for ((index, card) in hand.withIndex()) {                                                                  // * index, card sind in klammern, weil es ein syntax fehler geben würde. Es beinhaltet 2 informationen.
            println("$index. $card")                                                                        // *  Anzeige auf der Konsole der Karten des momentan Spielers
        }
    }
}