/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercrossing;

import cm3038.search.ActionStatePair;
import cm3038.search.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Liam Seddon
 */
public class PersonState implements State {

    public ArrayList<Person> listPersonsSouth, listPersonsNorth;
    private final boolean boatIsSouth;
    private final Boat boat;
    
    
    public PersonState(ArrayList<Person> listPersonsSouth, ArrayList<Person> listPersonsNorth, boolean boatIsSouth, Boat boat){
        this.listPersonsSouth = listPersonsSouth;
        this.listPersonsNorth = listPersonsNorth;
        this.boatIsSouth = boatIsSouth;
        this.boat = boat;
    }
        
    /**
     * 
     * @return All possible successor ActionStatePair
     */
    
    @Override
    @SuppressWarnings("unchecked")
    public List<ActionStatePair> successor() {
        
        List<ActionStatePair> successors = new ArrayList<>();
        
        // Get the list of people who are on the same side as the boat
        ArrayList<Person> src = boatIsSouth ? listPersonsSouth : listPersonsNorth;

        
        src.stream().filter((p) -> (p.isCanDrive())).forEachOrdered((p) -> {
            ArrayList<Person> pass = new ArrayList<>();
            if(p.getWeight() <= boat.getMaxWeight() && !pass.contains(p)){
                pass.add(p);

                PersonAction pAction = new PersonAction(pass, boatIsSouth);
                successors.add(new ActionStatePair(pAction,getSuccessor(pass)));
            }

            src.forEach((p2) -> {
                ArrayList<Person> pass2 = (ArrayList<Person>) pass.clone();
                if(p.getWeight()+p2.getWeight() <= boat.getMaxWeight() && !pass2.contains(p2)){
                    pass2.add(p2);
                    
                    PersonAction pAction2 = new PersonAction(pass2, boatIsSouth);
                    successors.add(new ActionStatePair(pAction2,getSuccessor(pass2)));
                }
                
                if(boat.getMaxCapacity()>2){
                    int boatCap = boat.getMaxCapacity()-2;
                    
                    ArrayList<Person> passN = (ArrayList<Person>) pass2.clone();
                    
                    while(boatCap!=0){
                        src.forEach((pers) -> {
                            int weight = 0;
                            weight = passN.stream().map((d) -> d.getWeight()).reduce(weight, Integer::sum) + pers.getWeight();
                            if (weight <= boat.getMaxWeight() && !passN.contains(pers)) {
                                passN.add(pers);
                                
                                PersonAction pActionN = new PersonAction(passN, boatIsSouth);
                                successors.add(new ActionStatePair(pActionN,getSuccessor(passN)));
                            }
                        });
                        boatCap-=1;
                    }
                }
            });
        });
        return successors;
    }

    /**
     * 
     * @param pass Passengers to move from south->north or north->south
     * @return Successor PersonState
     */
    @SuppressWarnings("unchecked")
    private PersonState getSuccessor(ArrayList<Person> pass) {
	ArrayList<Person> nSouth = (ArrayList<Person>) listPersonsSouth.clone();
	ArrayList<Person> nNorth = (ArrayList<Person>) listPersonsNorth.clone();
        
	ArrayList<Person> src = boatIsSouth ? nSouth : nNorth;
	ArrayList<Person> dest = boatIsSouth ? nNorth : nSouth;
        
	src.removeAll(pass);
	dest.addAll(pass);
                
	return new PersonState(nSouth, nNorth, !boatIsSouth, boat);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
                            
        String str = "";
        
        str = listPersonsNorth.stream().map((p) -> p.getName()+"("+p.getWeight()+")"+"["+p.isCanDrive()+"] ").reduce(str, String::concat);
        
        if(!boatIsSouth){
            str += "boat";
        }
        
        str += "\n~~~~~~~~~~~~~~~~~~RIVER~~~~~~~~~~~~~~~~~~\n";
        
        str = listPersonsSouth.stream().map((p) -> p.getName()+"("+p.getWeight()+")"+"["+p.isCanDrive()+"] ").reduce(str, String::concat);

        if(boatIsSouth){
            str += "boat\n\n";
        }
        else {
            str += "\n\n";
        }
        
        return str;
    }
    
    /**
     * 
     * @param paramObject
     * @return 
     */
    @Override
    public boolean equals(Object paramObject){
        
        if(!(paramObject instanceof PersonState)){
            return false;
        }
        PersonState state = (PersonState) paramObject;
        
        Collections.sort(listPersonsSouth);
        Collections.sort(listPersonsNorth);
        
        return listPersonsSouth.equals(state.listPersonsSouth) && listPersonsNorth.equals(state.listPersonsNorth);	
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode(){
        int hash = 0;
        hash = hash + (boatIsSouth ? 1 : 0);
        return hash;
    }
    
    public boolean isGoal(){
        return listPersonsSouth.isEmpty();
    }

    public boolean isBoatIsSouth() {
        return boatIsSouth;
    }

    public Boat getBoat() {
        return boat;
    }
    
    
}

