package rivercrossing;

/**
 *
 * @author Liam Seddon
 */
public class Boat {
    private int maxCapacity;
    private int maxWeight;
    
    public Boat(){}
    
    public Boat(int maxCapacity, int maxWeight){
        this.maxCapacity = maxCapacity;
        this.maxWeight = maxWeight;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}