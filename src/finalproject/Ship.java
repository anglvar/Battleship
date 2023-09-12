
package finalproject;

import java.io.Serializable;

abstract class Ship implements Serializable{
    protected int hits;
    protected int capacity;
    protected int size;
    protected boolean sunk;

    public int getHits() {
        return hits;
    }

    public int hitsLeft() {
        return capacity - hits;
    }

    public int getSize() {
        return size;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void incHits() {
        hits++;
        if (hits == capacity)
            sunk = true;
    }
    
    
}
