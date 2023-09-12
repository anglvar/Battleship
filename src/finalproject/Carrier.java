
package finalproject;

import java.io.Serializable;

public class Carrier extends Ship implements Serializable{
    
    public Carrier(){
        hits = 0;
        capacity = 5;
        size = 5;
        sunk = false;
    }
}
