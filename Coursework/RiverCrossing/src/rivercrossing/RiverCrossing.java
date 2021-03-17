/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercrossing;

import cm3038.search.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Liam Seddon
 */
public class RiverCrossing {

    private int maxBoatCapacity;
    private int maxBoatWeight;
    private int nbPersons;
            
    private Scanner reader;
    
    private final ArrayList<Person> listPersons = new ArrayList<>();
    private Boat boat;
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Person> listPersons = new ArrayList<>();
        listPersons.add(new Person("Adam", 100, true));
        listPersons.add(new Person("Betty", 90, false));
        listPersons.add(new Person("Claire", 50, true));
        listPersons.add(new Person("Dave", 30, false));
        // A boat have at least 2 places
        Boat boat = new Boat(2, 150);
        // Sort list by weight
        Collections.sort(listPersons);
        
        PersonState start = new PersonState(listPersons, new ArrayList<>(), true, boat);
        PersonState goal = new PersonState(new ArrayList<>(), listPersons, false, boat);
        
        AStar astar = new AStar(start, goal);
        
        Path path = astar.search();
        
        if ( path != null ) {
            path.print();
	}
        if(astar.search()==null){
            System.out.println("No solution");
        }
        else {
            System.out.println("Nodes visited: " + astar.nodeVisited + ", Cost: "+Cost.cost);
        }     
    }
    
    private void loadBoat(){
        System.out.println("                |\n" +
"              -----                    |\n" +
"              )___(                  -----\n" +
"                |                    )___(\n" +
"            ---------                  |\n" +
"           /         \\              -------\n" +
"          /___________\\            /       \\\n" +
"                |                 /_________\\\n" +
"         ---------------               |\n" +
"        /               \\        -------------\n" +
"       /                 \\      /             \\\n" +
"      /___________________\\    /_______________\\\n" +
"    ____________|______________________|__________\n" +
"     \\_                                        _/\n" +
"       \\______________________________________/\n" +
"~~..             ...~~~.           ....~~~...     ..~");
        reader = new Scanner(System.in);
        
        System.out.println("Creation of the ship:");
        System.out.print("Max capacity: ");
        maxBoatCapacity = reader.nextInt();
        System.out.print("Max weight: ");
        maxBoatWeight = reader.nextInt();
        boat = new Boat(maxBoatCapacity, maxBoatWeight);
    }
    
     private void configurePersons(){
        
        reader = new Scanner(System.in);

        System.out.print("\nNumber of persons: ");
        nbPersons = reader.nextInt();
                
        for(int i=1; i<nbPersons+1; i++){
            String personName;
            int personWeight;
            boolean canDrive;
            
            System.out.println("\nSet person "+i+": ");
            
            System.out.print("Name: ");
            personName = reader.next();
            
            System.out.print("Weight: ");
            personWeight = reader.nextInt();
            
            try {
                System.out.print("Can drive (true=default/false): ");
                boolean canDriveString = reader.nextBoolean();
                canDrive = canDriveString;
            } catch (InputMismatchException e) {
                canDrive = true;
                reader.next();
            }
            
            Person person = new Person(personName, personWeight, canDrive);
            listPersons.add(person);
        }
    }
     
     private void startRiverCrossing(){
        Collections.sort(listPersons);
        
        PersonState start = new PersonState(listPersons, new ArrayList<>(), true, boat);
        PersonState goal = new PersonState(new ArrayList<>(), listPersons, false, boat);
        
        AStar astar = new AStar(start, goal);
        
        Path path = astar.search();
        
        if ( path != null ) {
            path.print();
	}
        if(astar.search()==null){
            System.out.println("No solution");
        }
        else {
            System.out.println("Nodes visited: " + astar.nodeVisited + ", Cost: "+Cost.cost);
        }  
     }
    
}