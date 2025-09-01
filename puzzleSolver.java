import java.util.*;

public class puzzleSolver{
    int nodesExpanded = 0;
    int nodesExpandedForward = 0;
    int nodesExpandedBackward = 0;

    public List<String> BDS(puzzleState start, puzzleState goal){

        if(start.equals(goal)) return new ArrayList<>();

        Queue<puzzleState> forwardQ = new LinkedList<>(); 
        Queue<puzzleState> backwardQ = new LinkedList<>();

        Map<puzzleState,puzzleState> forwardParent = new HashMap<>();
        Map<puzzleState,puzzleState> backwardParent = new HashMap<>();

        Map<puzzleState,String> forwardMove = new HashMap<>(); 
        Map<puzzleState,String> backwardMove = new HashMap<>();

        forwardQ.add(start);
        backwardQ.add(goal);
        
        forwardParent.put(start,null);
        backwardParent.put(goal,null);

        while(!forwardQ.isEmpty() && !backwardQ.isEmpty()){

            //expand forward
            puzzleState meet = expandLayer(forwardQ,forwardParent,forwardMove,backwardParent);
            if(meet!=null){
                return getPath(meet,forwardParent,forwardMove,backwardParent,backwardMove);
            }

            //expand backwards
            meet = expandLayer(backwardQ,backwardParent,backwardMove,forwardParent);
            if(meet!=null){
                return getPath(meet,forwardParent,forwardMove,backwardParent,backwardMove);
            }
        }

        return null;
    }

    private puzzleState expandLayer(Queue<puzzleState> q, Map<puzzleState,puzzleState> parent, Map<puzzleState,String> move, Map<puzzleState,puzzleState> otherParent){
        if(q.isEmpty()) return null;

        puzzleState curr = q.poll();
        nodesExpanded++;

        for(puzzleState child : curr.getChildren()){
            if(!parent.containsKey(child)){
                parent.put(child,curr);
                move.put(child,child.move);
                q.add(child);

                //if other side has already visited the child then this is our meeting point
                if(otherParent.containsKey(child)){
                    return child;
                }
            }
        }

        return null;
    }

    private List<String> getPath(puzzleState meet, Map<puzzleState,puzzleState> forwardParent, Map<puzzleState,String> forwardMove, Map<puzzleState,puzzleState> backwardParent, Map<puzzleState,String> backwardMove){
        
        LinkedList<String> path = new LinkedList<>();
        puzzleState curr = meet;

        //meeting point to start
        while(forwardParent.get(curr)!=null){
            path.addFirst(forwardMove.get(curr));
            curr = forwardParent.get(curr);
        }

        //meeting point to goal
        curr = meet;
        while(backwardParent.get(curr)!=null){
            String move = backwardMove.get(curr);
            path.addLast(reverseMove(move));
            curr = backwardParent.get(curr);
        }

        System.out.println("Nodes expanded: "+nodesExpanded+"\n");
        return path;
    }

    private String reverseMove(String move){

        if (move == null) return null;
        switch (move) {
            case "UP": return "DOWN";
            case "DOWN": return "UP";
            case "LEFT": return "RIGHT";
            case "RIGHT": return "LEFT";
            default: return move;
        }
    }
}
