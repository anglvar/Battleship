
package finalproject;

import java.io.Serializable;

public class Battleship extends Ship implements Serializable{
    public Battleship(){
        hits = 0;
        capacity = 4;
        size = 4;
        sunk = false;
    }
}
