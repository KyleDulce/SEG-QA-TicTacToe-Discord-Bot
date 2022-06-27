package me.segQaGroupTen.discordBot;

public class GameLogic {

    static int numMoves = 0;
    private static final String emptyToken = "__";

    /**
     * Changes string board[row-1][column-1] to the given player token if the move is legal. A move
     * is legal if board[row-1][column-1] doesn't have a player token.
     * @param row Row where the player wants to put his token. Accepted values 1<= row <= 3.
     * @param column Column where the player wants to put his token. Accepted values 1 <= column <=3.
     * @param player Player trying to make the move.
     * @param gameBoard Board to modify
     * @return Returns message to be printed to the users
     */
    public static GameLogicResponse makeMove (int row, int column, Player player, String[][]gameBoard){
        //Change to make compatible with 2D array board
        row--;
        column--;

        //If a player token is already in the board[row][column] tell the player they can't play
        if (gameBoard[row][column].equals(emptyToken)){
            return GameLogicResponse.FAILURE;
        }

        gameBoard[row][column] = player.playerToken;
        numMoves+= 1;

        if (checkWin(gameBoard, row, column, player.playerToken)){

            return GameLogicResponse.WIN;
        }
        else if (checkTie()){

            return GameLogicResponse.TIE;
        }

        player.swapToken();
        return GameLogicResponse.SUCCESS;
    }

    private static boolean checkWin (String[][]gameBoard, int row, int column, String playerToken){

        //Assume all possible ways to win are true
        boolean rowCheck = true, columnCheck = true , diagonalCheck = true , antiDiagonalCheck = true;

        //Check row and column where the token was placed
        for (int i = 0; i < 3; i++){

            //If an element in the row is not a player token then the row is not valid
            if (!gameBoard[row][i].equals(playerToken))
                rowCheck = false;

            //If an element in the column is not a player token then the column is not valid
            if (!gameBoard[i][column].equals(playerToken))
                columnCheck = false;
        }

        //If row == column we placed a token in one of the diagonal squares R0C0, R1C1, R2C2
        if (row == column){
            for (int i = 0; i < 3; i++){
                if (!gameBoard[i][i].equals(playerToken)) {
                    diagonalCheck = false;
                    break;
                }
            }
        }
        else {
            diagonalCheck = false;
        }

        //If row + column == 2 we placed a token in one of the anti-diagonal squares R0C2, R1C1, R2C0
        if (row + column == 2){
            for (int i = 0; i < 3; i++){
                String test = gameBoard[i][2 - i];
                if (!gameBoard[i][2 - i].equals(playerToken)) {
                    antiDiagonalCheck = false;
                    break;
                }
            }
        }
        else{
            antiDiagonalCheck = false;
        }

        return (columnCheck || rowCheck || diagonalCheck || antiDiagonalCheck);
    }

    private static boolean checkTie(){
        return numMoves == 9;
    }

    public static void resetGame(String[][] gameBoard){

        numMoves = 0;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                gameBoard[i][j] = emptyToken;
            }
        }
    }
}
