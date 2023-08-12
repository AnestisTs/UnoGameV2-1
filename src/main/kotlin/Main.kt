fun main() {

    println(
        "$blue#     # $red#     # $yellow#######     $green  $blue #####     $green#    $yellow#     # $red####### \n" +
                "$blue#     # $red##    # $yellow#     #     $green  $blue#     #   $green# #   $yellow##   ## $red#       \n" +              // * ASCII code durch https://www.ascii-art-generator.org generiert
                "$blue#     # $red# #   # $yellow#     #     $green  $blue#        $green#   #  $yellow# # # # $red#       \n" +
                "$blue#     # $red#  #  # $yellow#     # $yellow#####$blue #  #### $green#     # $yellow#  #  # $red#####   \n" +
                "$blue#     # $red#   # # $yellow#     #     $green  $blue#     # $green####### $yellow#     # $red#       \n" +
                "$blue#     # $red#    ## $yellow#     #     $green  $blue#     # $green#     # $yellow#     # $red#       \n" +
                "$blue #####  $red#     # $yellow#######     $green  $blue #####  $green#     # $yellow#     # $red####### $reset"
    )

    var incorrectUserInput: Boolean = false

    do {

        try {
            println("Hallo, hast du lust auf ne Runde? Bitte antworte mit \"ja\" oder \"nein\"")
            var userInput = readln().lowercase()

            if (userInput == "ja") {
                var userInputPlayers = getNumPlayers()
                var playerNames: MutableList<String> = mutableListOf()
                println("Bitte geb noch die Namen für $userInputPlayers Spieler ein")
                for (i in userInputPlayers downTo 1){
                    playerNames.add(readln())
                }
                if (userInputPlayers in 2..4) {
                    val unoGame: UnoGame = UnoGame(userInputPlayers, playerNames)
                    unoGame.startGame()                         // ! evtl tauschen?
                    println("Du hast nicht zwischen 2-4 Spielern ausgewählt. Lies das nochmal und versuchs erneut..")
                }
            } else if (userInput == "nein") {
                println("Angst, Potter? Dann ein andern Mal.")
                incorrectUserInput = false
            } else {
                println("Das was du versuchst, macht keinen Sinn. Bitte versuche es erneut")
                incorrectUserInput = true
            }

        } catch (e: Exception) {
            println("Das was du versuchst, macht keinen Sinn. Bitte versuche es erneut.")
            println(e.message)
            incorrectUserInput = true
        }
    } while (incorrectUserInput)


}

