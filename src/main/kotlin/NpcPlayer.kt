class NpcPlayer(name: String) : Player(name) {



    fun chooseCardNpc(currentCard: Card) : Card{

        // * lambda funktion zum filtern. Gibt eine Liste mit den passenden Karten wieder.
        val npcChoseCard = playerHand.hand.filter { card -> currentCard.color == card.color || currentCard.value == card.value }
        val npcChosenCard = npcChoseCard.random()
        println("$name hat $npcChosenCard gespielt")
        return npcChosenCard
    }

}



