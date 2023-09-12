
package finalproject;

import java.io.Serializable;

public class Player implements Serializable{
    private int number;
    private boolean turn;
    Ship [] myShips = {new Carrier(), new Battleship(), new Cruiser(), new Submarine(), new Destroyer()};

    public Player(int number){
        this.number = number;
        turn = false;
        
    }
    public boolean isTurn() {
        return turn;
    }

    public Ship[] getMyShips() {
        return myShips;
    }

    public void setTurn() {
        if (turn == true)
            turn = false;
        else 
            turn = true;
    }
    
    @Override
    public String toString(){
        return "Player " + number;
    }
    
}
