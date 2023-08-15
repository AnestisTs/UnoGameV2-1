class NpcPlayer(name: String) : Player(name) {



    fun chooseCardNpc(playerHand: List<Card>, currentCard: Card) : Card{

        // * lambda funktion zum filtern. Gibt eine Liste mit den passenden Karten wieder.
        val npcChoseCard = playerHand.filter { card -> currentCard.color == card.color || currentCard.value == card.value }
        npcChoseCard.random()

        return npcChoseCard.random()
    }

}


// ! TODO NPC PLAYER erstellen. erbt von player. funktion für kartenwahl erstellen. cuurentcard & playerhand entscheidet
// ! TODO^ welche Karte gespielt wird. .filter benutzen, um eine Liste für eine Liste für nur Legbare Karten zu erstellen.
// ! TODO^ liste.random würde die Karte ausgeben können
// ! TODO^ if/else für den NPC anstelle von readln wie bei dem Menschlichen Spieler

// ! hier drunter steht der code für die mögliche einbaute des npcplayers.

