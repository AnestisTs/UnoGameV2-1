class DrawTwoCard(color: CardColor, value: CardValue) : Card(color, value) {


    fun drawTwo(deck: MutableList<Card>) : MutableList<Card>{

        var drawnCards : MutableList<Card> = mutableListOf()
        drawnCards.add(deck.removeAt(0))
        drawnCards.add(deck.removeAt(0))
        return drawnCards
    }



}