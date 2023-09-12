
package finalproject;

import java.io.Serializable;

public class Destroyer extends Ship implements Serializable{
    public Destroyer(){
        hits = 0;
        capacity = 2;
        size = 2;
        sunk = false;
    }
}
