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
                var userInputPlayers = getNumPlayers(false, 4)
                var playerNames: MutableList<String> = mutableListOf()
                println("Bitte geb noch die Namen für $userInputPlayers Spieler ein")
                // * für jeden spieler, den ich eingegeben bhabe, läuft die schleife einmal durch, dass man einen namen eingeben kann.
                for (i in userInputPlayers downTo 1) {
                    playerNames.add(readln())
                }
                if (userInputPlayers < 4) {
                    var maximumNpcPlayers = 4 - userInputPlayers
                    var userInputNpcPlayers = getNumPlayers(true, maximumNpcPlayers)
                    if (userInputNpcPlayers > 0) {
                        for (i in 1 .. userInputNpcPlayers) {
                            playerNames.add("Npc$i")
                        }
                        userInputPlayers += userInputNpcPlayers
                    }
                }


                if (userInputPlayers in 2..4) {
                    val unoGame: UnoGame = UnoGame(userInputPlayers, playerNames)
                    unoGame.startGame()

                } else {
                    incorrectUserInput = true
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
            incorrectUserInput = true
        }
    } while (incorrectUserInput)
    println()

}

