class NpcPlayer(name: String) : Player(name) {

    var hand : MutableList<Card> = mutableListOf<Card>()


    fun chooseCardNpc(playerHand: List<Card>, currentCard: Card){


        val playableCards = hand.filter { card -> currentCard.color == card.color || currentCard.value == card.value }
        playableCards.random()



    }




}


// ! TODO NPC PLAYER erstellen. erbt von player. funktion für kartenwahl erstellen. cuurentcard & playerhand entscheidet
// ! TODO^ welche Karte gespielt wird. .filter benutzen, um eine Liste für eine Liste für nur Legbare Karten zu erstellen.
// ! TODO^ liste.random würde die Karte ausgeben können
// ! TODO^ if/else für den NPC anstelle von readln wie bei dem Menschlichen Spieler

// val npcPlayer = players.get(currentPlayer)
