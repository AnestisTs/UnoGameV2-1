class SkipCard(color: CardColor, value: CardValue) : Card(color, value) {


    fun skipPlayer(clockwisePlayerTurns: Boolean, currentPlayer: Int, numberPlayers: Int): Int {
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