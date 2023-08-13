open class Player(var name: String) {

    var playerHand: PlayerHand = PlayerHand(name)
  /* open fun battleCry(hand: PlayerHand){

       if(hand.size){

           println(" $name: ZACK, Uno letzte Karte.. Deal with it")
       }

       println(" $name: ZACK, Uno letzte Karte.. Deal with it")

    }
*/
   open fun countingWins(){

   }


   open fun showStats(){                // ? zeigt die stats


    }

    open fun addStackToDeck(deck: MutableList<Card>, stack: MutableList<Card>, currentCard: Card){
 // ! doppelte funktion ( siehte playerhand.kt ) sollte ich vielleicht mal schlafen gehen?
        stack.removeAt(0)                    // Karte wird runter genommen und zum stack hinzugegüht
        deck.addAll(stack)                                              // * Mit addAll werden Listen zu Listen hinzugefügt
        stack.clear()
        if (currentCard != null){                                       // ! Card könnte auch null sein, deswegen muss hier gecheckt werden, ob es ungleich null ist.
            stack.add(currentCard!!)      // ! ...könnte !! aufgrund der änderung der card jetzt auch entfernen?       // ! *currentCard!!* Smartcast vorschlag von IntelliJ
        println()
        }
    }







}