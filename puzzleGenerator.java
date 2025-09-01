import java.util.*;

public class puzzleGenerator {
    static Random rand = new Random();
    
    public static int[][] generateSolvablePuzzle(){
        int[] arr;

        do{
            arr = shuffleArray();
        } while(!isSolvable(arr));

        System.out.println("Solvable puzzle generated!!\n");
        return toBoard(arr);
    }

    private static int[] shuffleArray(){
        List<Integer> nums = new ArrayList<>();

        for(int i=0;i<9;i++){
            nums.add(i);
        }
        Collections.shuffle(nums);

        return nums.stream().mapToInt(i->i).toArray();
    }

    public static boolean isSolvable(int[] arr){
        int invCount = 0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]!=0 && arr[j]!=0 && arr[i]>arr[j]){
                    invCount++;
                }
            }
        }

        return invCount%2==0;
    }

    public static int[][] toBoard(int[] arr){
        int[][] board = new int[3][3];

        for(int i=0;i<9;i++){
            board[i/3][i%3] = arr[i];
        }

        return board;
    }
}
