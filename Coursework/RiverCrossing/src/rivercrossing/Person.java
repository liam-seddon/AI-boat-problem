package rivercrossing;

/**
 *
 * @author Liam Seddon
 */
public class Person implements Comparable<Person> {
    
    private String name;
    private int weight;
    private boolean canDrive;
    
    public Person(){}
    
    public Person(String name, int weight, boolean canDrive){
        this.name = name;
        this.weight = weight;
        this.canDrive = canDrive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isCanDrive() {
        return canDrive;
    }

    public void setCanDrive(boolean canDrive) {
        this.canDrive = canDrive;
    }
    
    @Override
    public int compareTo(Person o) {
        return this.weight - o.getWeight();
    }
}