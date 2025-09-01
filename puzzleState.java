import java.util.*;

public class puzzleState {
    int[][] board;
    int blankRow, blankCol;
    puzzleState parent;
    String move;

    puzzleState(int[][] board, puzzleState parent, String move) {
        this.board = new int[3][3];
        for(int i=-0; i<3; i++) {
            this.board[i] = Arrays.copyOf(board[i], 3);
        }
        findBlank();
        this.parent = parent;
        this.move = move;
    }

    private void findBlank(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(this.board[i][j] == 0){
                    this.blankRow = i;
                    this.blankCol = j;
                }
            }
        }
    }

    boolean isGoal(int[][] goal){
        for(int i=0;i<3;i++){
            if(!Arrays.equals(this.board[i], goal[i])){
                return false;
            }
        }
        return true;
    }

    List<puzzleState> getChildren(){
        List<puzzleState> children = new ArrayList<>();

        if(blankRow>0){
            children.add(makeMove(blankRow-1, blankCol,"UP"));
        }
        if(blankRow<2){
            children.add(makeMove(blankRow+1, blankCol,"DOWN"));
        }
        if(blankCol>0){
            children.add(makeMove(blankRow, blankCol-1,"LEFT"));
        }
        if(blankCol<2){
            children.add(makeMove(blankRow, blankCol+1,"RIGHT"));
        }
        return children;
    }

    private puzzleState makeMove(int newRow,int newCol, String move){
        int[][] newBoard = new int[3][3];

        for(int i=0;i<3;i++){
            newBoard[i] = Arrays.copyOf(this.board[i],3);
        }
        newBoard[blankRow][blankCol] = newBoard[newRow][newCol];
        newBoard[newRow][newCol] = 0;

        return new puzzleState(newBoard, this, move);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof puzzleState)){
            return false;
        }
        puzzleState other = (puzzleState) o;
        
        for(int i=0;i<3;i++){
            if(!Arrays.equals(this.board[i], other.board[i])){
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode(){
        return Arrays.deepHashCode(this.board);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<3;i++){
            sb.append(Arrays.toString(this.board[i]));
            sb.append("\n");
        }

        return sb.toString();
    }
}
