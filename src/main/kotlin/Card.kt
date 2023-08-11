open class Card (val color: CardColor, val value: CardValue){
      override fun toString(): String {
        if (color.cardColor == "Rot") {
            return "$red ${color.cardColor} - ${value.numbers}  $reset"
        } else if (color.cardColor == "Grün") {
            return "$green ${color.cardColor} - ${value.numbers} $reset"
        }else if (color.cardColor == "Blau") {
            return "$blue ${color.cardColor} - ${value.numbers} $reset"
        }else{
            return "$yellow ${color.cardColor} - ${value.numbers} $reset"
        }
    }

}




enum class CardValue(val numbers: String){
    TWO("2",),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    SKIP("Skip"),
    REVERSE("Reverse"),
    DRAWTWO("DrawTwo")
}

enum class CardColor(var cardColor: String){
    RED("Rot"),
    BLUE("Blau"),
    YELLOW("Gelb"),
    GREEN("Grün")
}

