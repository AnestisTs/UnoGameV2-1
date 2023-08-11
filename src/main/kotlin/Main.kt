fun main() {

   println("$blue#     # $red#     # $yellow#######     $green  $blue #####     $green#    $yellow#     # $red####### \n" +
           "$blue#     # $red##    # $yellow#     #     $green  $blue#     #   $green# #   $yellow##   ## $red#       \n" +              // * ASCII code durch https://www.ascii-art-generator.org generiert
           "$blue#     # $red# #   # $yellow#     #     $green  $blue#        $green#   #  $yellow# # # # $red#       \n" +
           "$blue#     # $red#  #  # $yellow#     # $yellow#####$blue #  #### $green#     # $yellow#  #  # $red#####   \n" +
           "$blue#     # $red#   # # $yellow#     #     $green  $blue#     # $green####### $yellow#     # $red#       \n" +
           "$blue#     # $red#    ## $yellow#     #     $green  $blue#     # $green#     # $yellow#     # $red#       \n" +
           "$blue #####  $red#     # $yellow#######     $green  $blue #####  $green#     # $yellow#     # $red####### $reset")


    try {
        println("Hallo, hast du lust auf ne Runde? Bitte antworte mit \"ja\" oder \"nein\"")
        var userInput = readln().lowercase()

        if (userInput == "ja"){
            println("Bitte gib die Anzahl an Spielern an (2-4)")
            var userInputPlayers = readln().toInt()

            if (userInputPlayers == 2..4){

            }
        }

    }
    println("Hallo, hast du lust auf ne Runde? Bitte antworte mit \"ja\" oder \"nein\"")
    var userInput = readln().lowercase()
    println("Bitte gebe noch die Anzahl an Spielern an (2-4)")
    var userInputPlayers = readln().toInt()

    val unoGame: UnoGame = UnoGame(userInputPlayers)

    if (userInput == "ja"){
        println("Viel Glück und viel erfolg")
        unoGame.startGame()

    }else{
        println("Angst, Potter? Dann ein andern Mal.")
    }


}

