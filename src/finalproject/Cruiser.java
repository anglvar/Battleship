
package finalproject;

import java.io.Serializable;

public class Cruiser extends Ship implements Serializable{
    public Cruiser(){
        hits = 0;
        capacity = 3;
        size = 3;
        sunk = false;
    }
}
