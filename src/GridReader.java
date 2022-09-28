import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class GridReader
{
    private char[][] grid;
    private String filename;
    File file;

    public GridReader(String filename) throws FileNotFoundException
    {
        this.filename = filename;
        file = new File(filename);
        if(file.exists())
        {
            initializeGrid(file);
            populateGrid(file);
        }
    }
    public String getFileName()
    {
        return filename;
    }



    public char[][] getCopy()
    {
        if(file.exists())
        {
            char[][] copyGrid = new char[grid.length][];
            for(int r = 0 ; r < grid.length; r++)
            {
                copyGrid[r] = new char[grid[r].length];

                for(int c = 0 ; c < grid[r].length ; c++)
                {
                    copyGrid[r][c] = grid[r][c];
                }
            }
            return copyGrid;
        }
        else
        {
            return null;
        }
    }

    private void populateGrid(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);

        for(int r = 0 ; r < grid.length ; r++)
        {
            char[] chars = scanner.nextLine().toCharArray();

            for(int c = 0 ; c < grid[r].length ; c++)
            {
                grid[r][c] = chars[c];
            }
        }

        scanner.close();
    }

    private void initializeGrid(File file) throws FileNotFoundException
    {
        initializeGridRows(file);
        initializeGridColumns(file);


    }
    private void initializeGridRows(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);
        int rows = 0;

        do
        {
            rows++;
            scanner.nextLine();
        }
        while(scanner.hasNextLine());

        grid = new char[rows][];

        scanner.close();
    }
    private void initializeGridColumns(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);

        for(int i = 0 ; i < grid.length ; i ++)
        {
            grid[i] = new char[scanner.nextLine().length()];
        }

        scanner.close();
    }

    @Override
    public java.lang.String toString()
    {
        if(file.exists())
        {
            StringBuffer buffer = new StringBuffer();

            for(int r = 0 ; r < grid.length ; r++)
            {
                for(int c = 0 ; c < grid[r].length ; c++)
                {
                    buffer.append(grid[r][c]);
                }

                buffer.append(System.lineSeparator());
            }

            return buffer.toString();
        }
        else
        {
            return null;
        }
    }
}