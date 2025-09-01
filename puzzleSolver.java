import java.util.*;

public class puzzleSolver {
    int nodesExpanded = 0;

    public List<String> IDS(puzzleState state, int[][] goal){
        // System.out.println("Starting the IDS search...\n");

        for(int depth = 0;;depth++){
            Set<puzzleState> visited = new HashSet<>();
            // System.out.println("Calling DLS with depth: "+depth+"\n");
            puzzleState result = DLS(state, goal, depth, visited);

            if(result!=null){
                System.out.println("Goal found at depth: "+depth);
                System.out.println("Nodes expanded: "+nodesExpanded);

                return getPath(result);
            }  
        }
    }

    private puzzleState DLS(puzzleState state, int[][] goal, int depth, Set<puzzleState> visited) {

        if(state.isGoal(goal)) return state;
        if(depth==0) return null;

        // System.out.println("-----------------------------------------------");
        // System.out.println("Expanding node:- \n"+state);
        // System.out.println("Moved: "+state.move+"\n");
        
        visited.add(state); 
        nodesExpanded++;

        for(puzzleState child : state.getChildren()){

            if (state.parent != null && child.equals(state.parent)) continue;

            if(!visited.contains(child)){
                puzzleState result = DLS(child, goal, depth-1, visited);
                if(result!=null) return result;
            }
        }

        visited.remove(state);
        return null;
    }

    private List<String> getPath(puzzleState goalState){
        LinkedList<String> path = new LinkedList<>();
        puzzleState currentState = goalState;

        while(currentState.parent!=null){
            path.addFirst(currentState.move);
            currentState = currentState.parent;
        }

        return path;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }
}
