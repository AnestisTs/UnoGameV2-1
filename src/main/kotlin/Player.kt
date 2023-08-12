open class Player(var name: String) {

    var playerHand: PlayerHand = PlayerHand(name)
   open fun battleCry(){

        println(" $name: Zeit für ein... Dudududu Duellllll!")

    }

   open fun countingWins(){


    }


   open fun showStats(){                // ? zeigt die stats


    }

    open fun addStackToDeck(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){

        stack.removeAt(0)       // ! was passiert hier?             // Karte wird runter genommen und zum stack hinzugegüht
        deck.addAll(stack)                                              // * Mit addAll werden Listen zu Listen hinzugefügt
        stack.clear()              // ! hier fehlerhaft
        if (currentCard != null){                                       // ! Card könnte auch null sein, deswegen muss hier gecheckt werden, ob es ungleich null ist.
            stack.add(currentCard!!)                                    // ! *currentCard!!* Smartcast vorschlag von IntelliJ
        }
    }

   open fun jokerMove(clockwisePlayerTurns: Boolean, currentPlayer: Int, numberPlayers: Int) : Int{                // ? Fähigkeit, eine Runde zu überspringen, quasi direkt noch eine Karte legen zu dürfen

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





}