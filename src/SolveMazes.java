import java.io.FileNotFoundException;
import java.util.Scanner;

class SolveMazes
{
    public static void main(String[] args) throws FileNotFoundException
    {
        MazeSolver solver = new MazeSolver();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        while(!input.equals("stop"))
        {
            GridReader maze = new GridReader(input);
            solver.solveMaze(maze.getCopy());

            int percentage = (int) (solver.getPerformance() * 100);

            System.out.print("Solution: ");
            if(solver.getMoves() != null)
            {
                for(String move : solver.getMoves())
                {
                    System.out.print(move + " ");
                }

                System.out.println();
            }
            else
            {
                System.out.println("No Solution!");
            }

            System.out.println("Number of cells visited: " + solver.getNumCellsVisited());
            System.out.println("Percent Correct: " + percentage + "%");

            input = scanner.next();
        }
    }
}