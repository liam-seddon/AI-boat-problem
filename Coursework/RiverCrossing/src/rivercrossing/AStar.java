package rivercrossing;

import cm3038.search.Node;
import cm3038.search.State;
import cm3038.search.informed.BestFirstSearchProblem;
import java.util.Collections;

/**
 *
 * @author Liam Seddon
 */
public class AStar extends BestFirstSearchProblem {
    
    /**
     * 
     * @param start
     * @param goal 
     */
    public AStar(State start, State goal) {
        super(start, goal);
    }

    /**
     * 
     * @param node
     * @return 
     */
    @Override
    public double evaluation(Node node) {
        return node.getCost() + this.heuristic(node.state);
    }
    
    /**
     * 
     * @param state
     * @return 
     */
    public int heuristic(State state){
        
        PersonState sState = (PersonState) state;

        if(sState.isBoatIsSouth()){

            return sState.isGoal() ? 0 : Collections.max(sState.listPersonsSouth).getWeight();
        }
        else {
            return sState.isGoal() ? 0 : Collections.min(sState.listPersonsNorth).getWeight();
        }
        
    }

    /**
     * 
     * @param state
     * @return 
     */
    @Override
    public boolean isGoal(State state) {
        return state.equals(this.goalState);
    }
}