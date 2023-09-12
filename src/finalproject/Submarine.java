
package finalproject;

import java.io.Serializable;

public class Submarine extends Ship implements Serializable{
    public Submarine(){
        hits = 0;
        capacity = 3;
        size = 3;
        sunk = false;
    }
}
