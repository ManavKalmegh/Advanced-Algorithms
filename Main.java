import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Enter\n1:Generate random solvable configs.\n2:Enter custom board.\n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        int[][] startBoard;

        if(choice==1){
            System.out.println("Generating random solvable configs...");
            startBoard = puzzleGenerator.generateSolvablePuzzle();
        }
        else if (choice == 2) {
            System.out.println("Enter 9 numbers row by row (use 0 for blank):");
            int[] arr = new int[9];
            for (int i = 0; i < 9; i++) {
                arr[i] = sc.nextInt();
            }

            //check if all nums are entered correctly or not
            for (int i = 0; i < 9; i++) {
                if (arr[i] < 0 || arr[i] > 8) {
                    System.out.println("Invalid input!!\n");
                    return;
                }
            }

            if (!puzzleGenerator.isSolvable(arr)) {
                System.out.println("This configuration is NOT solvable. Please try again with a different input.\n");
                return;
            }

            startBoard = puzzleGenerator.toBoard(arr);
        }
        else{
            System.out.println("Invalid choice!!\n");
            return;
        }

        int[][] goalBoard = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };

        puzzleState start = new puzzleState(startBoard, null, null);

        System.out.println("\nInitial State:");
        System.out.println(start);

        puzzleSolver solver = new puzzleSolver();
        List<String> path = solver.IDS(start, goalBoard);

        // System.out.println("Goal State:");
        // for (int i = 0; i < 3; i++) {
        //     System.out.println(Arrays.toString(goalBoard[i]));
        // }

        System.out.println("\nSequence of Moves (" + path.size() + " steps):");
        System.out.println(path + "\n");

    }
}
