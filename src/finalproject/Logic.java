
package finalproject;

import java.io.Serializable;

public class Logic implements Serializable{
    private Board board1 = new Board();
    private Board board2 = new Board();
    private Player player1 = new Player(1);
    private AI player2 = new AI();
    private Player currentPlayer;
    private Board currentBoard;
    private String green = "\u001B[32m";
    
    public Logic(){
        currentPlayer = player1;
        currentPlayer.setTurn();
    }
    
    public void AIPlaceShips(){
        for (int i = 0; i < 5 ; i++){ 
            boolean valid = false;
            do {
                int [] cords = player2.placeShips(player2.getMyShips()[i].size);
                valid =  setShips(cords[0], cords[1], i);
            } while (valid == false);
        }
    }
    
    public boolean setShips(int x, int y, int ship){
        if (currentPlayer == player1)
            currentBoard = board1;
        else 
            currentBoard = board2;

        // Tests if the ship will be in bounds
        if (x-1 + currentPlayer.getMyShips()[ship].size > 10){
            System.out.println(green + "Error. Ship will be out of bounds try again mate.");
            return false;
        }

        //  tests if the ships will overlap and returns false if so.
        for (int i = 0; i < currentPlayer.getMyShips()[ship].size; i++){
            if (currentBoard.getSquares()[x-1][y-1].isFilled() == true){
                if (currentPlayer == player1)
                    System.out.println(green + "Ships will overlap. Try again.");
                return false;
            }
        }
        
        for (int i = 0; i < currentPlayer.getMyShips()[ship].size; i++) {
            currentBoard.getSquares()[x - 1][y - 1].setShip(currentPlayer.getMyShips()[ship]);  // sets ship on board
            currentBoard.getSquares()[x - 1][y - 1].setStatus("filled");                      // sets status to filled
            ++x;
        }

        return true;
    }
    
    public void flipSquares(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++){
                board1.getSquares()[i][j].flipSquare();     //flips so other user cannot see ships
                board2.getSquares()[i][j].flipSquare();
            }
        }
    }
    
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    
    public void changeCurrentPlayer(){
        if (currentPlayer == player1){
            player1.setTurn();
            player2.setTurn();
            currentPlayer = player2;
        }
        else{
            player1.setTurn();
            player2.setTurn();
            currentPlayer = player1;
        }
    }
    
    public boolean AITurn(){
        boolean error = true;
        do {
            int[] cords = player2.shoot();

            currentBoard = board1;
            Square currentSquare = currentBoard.getSquares()[cords[0]- 1][cords[1] - 1];
            Square downSquare = null;

            if (cords[0] != 10)
                downSquare = currentBoard.getSquares()[cords[0]][cords[1] - 1];

            switch (currentSquare.getStatus()){
                case "filled":
                    currentSquare.setStatus("hit");
                    currentSquare.getShip().incHits();
                    if (currentSquare.getShip().isSunk()) {
                        player2.setNextHitNull();
                        System.out.println("\n" + green + "Player 2 Hit your ship. The ship is sunk");
                    } else {
                        System.out.println("\n" + green + "Player 2 Hit your ship. " + currentSquare.getShip().hitsLeft() + " hits left");
                        if (downSquare != null && downSquare.getStatus().equals("hit"))
                            player2.nextHit(cords[0], cords[1], "up");
                        else 
                            player2.nextHit(cords[0], cords[1], "down");
                    }
                    error = false;
                    break;
                case "empty":
                    currentSquare.setStatus("miss");
                    System.out.println("\n"+ green + "Player 2 Missed");
                    error = false;
                    player2.setNextHitNull();
                    break;
                default: error = true;
            }
        } while (error == true);
        System.out.println(green + "==========================");
        System.out.println(green + "==========================");
        currentBoard.display();
        
        // if all oppositePlayer ships are sunk, then currentPlayer has won.
        for (int i = 0; i < 5; i++) {
            if (!player1.getMyShips()[i].isSunk()) {
                return false;            // if one ship is not sunk, loop will break
            }

        }
        changeCurrentPlayer();
        return true;
        
    }
    public boolean playerTurn(int x, int y){
        currentBoard = board2;
        
        Square currentSquare = currentBoard.getSquares()[x-1][y-1];
        switch (currentSquare.getStatus()) {
            case "hit":
                System.out.println(green + "Already shot here. It was a HIT");
                break;
            case "miss":
                System.out.println(green + "Already shot here. It was a MISS");
                break;
            case "filled":
                currentSquare.setStatus("hit");
                currentSquare.getShip().incHits();
                if (currentSquare.getShip().isSunk()) {
                    System.out.println(green + "HIT. The ship is sunk");
                } else {
                    System.out.println(green + "Hit. " + currentSquare.getShip().hitsLeft() + " hits left");
                }
                break;
            case "empty":
                currentSquare.setStatus("miss");
                System.out.println(green + "MISS");
                break;
        }
        currentBoard.display();
        
        // if all oppositePlayer ships are sunk, then currentPlayer has won.
        for (int i = 0; i < 5; i++){
            if (!player2.getMyShips()[i].isSunk()){
                return false;        // if one ship is not sunk, loop will break
            }              
        }
        return true;
    }
    
    public void showEnemy(){
        System.out.println(green + "(Enemy board)");
        board2.display();

    }

    public void shoMine(){
        System.out.println(green + "(Your board)");
        board1.display();
    }
}
