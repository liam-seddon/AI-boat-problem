package rivercrossing;

import cm3038.search.Action;
import java.util.ArrayList;
/**
 *
 * @author Liam Seddon
 */
public class PersonAction extends Action {
    
    ArrayList<Person> person;
    boolean boatIsSouth;
    
    /**
     * 
     * @param person
     * @param boatIsSouth 
     */
    public PersonAction(ArrayList<Person> person, boolean boatIsSouth) {
        this.person = person;
        this.boatIsSouth = boatIsSouth;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Person> getPerson() {
        return person;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        for(int i=0; i<person.size(); i++){
            Cost.cost += person.get(i).getWeight();
        }
        String str = "Moving ";
        
        str = this.getPerson().stream().map((p) -> p.getName()+"("+p.getWeight()+")["+p.isCanDrive()+"] - ").reduce(str, String::concat);
        
        if(this.boatIsSouth){
            str += "SOUTH_TO_NORTH\n";
        }
        else {
            str += "NORTH_TO_SOUTH\n";
        }
        
        return str;
    }
    
}
