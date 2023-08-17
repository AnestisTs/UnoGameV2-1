open class Card (val color: CardColor, val value: CardValue){

    // * Ich habe hier eine Ausgabe des Objektes erstellt.
    // * hier setze ich für meine println eine ordentliche Ausgabe der Card. (Z.B. Grün 5)
    override fun toString(): String {
        // * muss auf color.cardcolor zugreifen, um die String aus der enum class zu bekommen.
        // * Zusätlich hab ich eine Farbausgabe in der Konsole hinzugefügt. z.b $red kommt aus der utils.kt
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



// * habe mich für enums entschieden statt meiner ursprünglichen Liste für das Kartendeck, da ich unteranderem bei der Farbausgabe der Karten
// * eine verschiedene Farbe für Kartenfarbe und Kartenzahl hatte. z.b. grün 5 war die Kartenfarbe grün, und der Wert war Rot.
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

