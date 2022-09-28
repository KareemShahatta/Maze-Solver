import java.util.ArrayList;

class MazeSolver
{
    private final char OPEN = '.' ;
    private final char BLOCKED = '#';
    private final char START = 'S';
    private final char GOAL = 'G';
    private final char MAKRED = '+';
    private final char UNMARKED = 'x';
    private char[][] maze;
    private ArrayList<String> mazePath;
    private int numCellsVisited;
    private int mazesSolved;
    private int mazesTried;

    private int columns;
    private int rows;

    public boolean solveMaze(char[][] maze)
    {
        this.maze = maze;

        if(maze != null)
        {
            mazePath  = new ArrayList<>();
            int startX = 0;
            int startY = 0;

            mazesTried++;

            columns = getColumns();
            rows = maze.length;

            for(int y = 0 ; y < maze.length; y++)
            {
                for(int x = 0 ; x < maze[y].length ; x++)
                {
                    if(maze[y][x] == START)
                    {
                        startX = x;
                        startY = y;
                    }
                }
            }

            if(findPath(startX , startY))
            {
                mazesSolved++;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }


    }

    public boolean findPath(int x , int y)
    {
        if(( 0 <= x && x < columns) && ( 0 <= y && y < rows)) //Checks boundaries
        {
            if(maze[y][x] == BLOCKED) //Check valid movements
            {
                return false;
            }
            else
            {
                if(maze[y][x] == GOAL) //Checks goal
                {
                    return true;
                }
                else
                {
                    if(maze[y][x] == OPEN || maze[y][x] == START || maze[y][x] != MAKRED) //Check movement
                    {
                        maze[y][x] = MAKRED;

                        if(findPath(x,y-1)) //North 
                        {
                            mazePath.add("North");
                            numCellsVisited++;
                            return true;
                        }
                        else if(findPath(x+1,y))  //East 
                        {
                            mazePath.add("East");
                            numCellsVisited++;
                            return true;
                        }
                        else if(findPath(x,y+1)) //South 
                        {
                            mazePath.add("South");
                            numCellsVisited++;
                            return true;
                        }

                        else if(findPath(x-1,y)) //West
                        {
                            mazePath.add("West");
                            numCellsVisited++;
                            return true;
                        }
                        else
                        {
                            maze[y][x] = UNMARKED;
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        else
        {
            return false;
        }

    }

    public String[] getMoves()
    {
        if(maze != null)
        {
            String[] moves = new String[mazePath.size()];
            for(int i = 0 ; i < mazePath.size() ; i++)
            {
                moves[i] = mazePath.get((mazePath.size() - 1) - i);
            }
            return moves;
        }
        else
        {
            return null;
        }
    }

    public int getNumCellsVisited()
    {
        return numCellsVisited;
    }

    public double getPerformance()
    {
        double x = mazesSolved;

        double y = mazesTried;

        return ((x/y));
    }

    private int getColumns()
    {
        int columns = 0;

        for(int r = 0 ; r < maze.length; r++)
        {
            if(maze[r].length > columns)
            {
                return columns = maze[r].length;
            }
        }

        return columns;
    }
}