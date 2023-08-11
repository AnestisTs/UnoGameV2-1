class ReverseCard(color: CardColor, value: CardValue) : Card(color, value) {


    fun reverseIt(clockwisePlayerTurns: Boolean): Boolean {


        return !clockwisePlayerTurns


    }


}

