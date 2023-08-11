open class Player(var numberplayers: Int, var name: String, var isExperienced: Boolean,  ) {

   open fun battleCry(){

        println("Zeit für ein... Dudududu Duellllll!")

    }

   open fun countingWins(){


    }


   open fun showStats(){                // ? zeigt die stats


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