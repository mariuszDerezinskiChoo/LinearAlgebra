import java.util.*;
import java.io.*;
public class ReducedEchelonForm
{
    public static void main(String[] args)
    {
        Scanner kbReader = new Scanner(System.in);
        System.out.print("Enter an Array (ex: [1,2][3,4][5,6])");
        double[][] tester = null;
        try {
             tester = arrayReader(kbReader.nextLine());
             printarr(tester);
             System.out.println("\n\n");
             printarr(rref(tester));
        } catch(Exception e) {
             System.out.println("invalid syntax");
        }
    }

    public static double[][] rref(double[][] input)
    {
        /*
         * Establish echelon form
         */
        int pivotRow = 0;
        int pivotColumn = 0;
        //ArrayList<Double> pivots = new ArrayList<Double>();
        while(pivotRow < input.length && pivotColumn < input[0].length)
        {
            int checking = pivotRow;
            boolean found = false;
            while((!found) && checking < input.length)
                {
                    if(input[checking][pivotColumn] == 0)
                        checking++;
                    else
                    {
                        //System.out.println(pivotRow + " " + checking);
                        //pivots.add(new Double(pivotColumn));
                        swap(input, pivotRow, checking);
                        fixColumn(pivotColumn, pivotRow, input);
                        pivotRow++;
                        break;
                    }
                }
                pivotColumn++;
                //printarr(input);
        }
        /*
         * clean top to establish reduced row echelon form
         */
        //printarr(input);
        
        for(int i = input.length - 1; i >= 0; i--)
        {
            for(int j = 0; j < input[i].length; j++)
            {
                if(input[i][j] < -.00001 || input[i][j] > .00001)
                {
                    cleanAbove(input, i, j);
                    
                    /*
                     * make the leading entry one
                     */
                    double dividing = input[i][j];
                    for(int column = 0; column < input[i].length; column++)
                        input[i][column] = input[i][column] / dividing;
                    break;
        
                    
                  
                    
                }
            }
        }
        
        return input;
    }
    public static void cleanAbove(double[][] arr, int row, int column)
    {
        for(int i = row - 1; i >= 0; i--)
        {
            double constant = arr[i][column] / arr[row][column];
            for(int j = 0; j < arr[i].length; j++)
                arr[i][j] = arr[i][j] - constant * arr[row][j];
        }
    }
    public static void fixColumn(int column, int row, double[][] arr)
    {
        for(int i = row + 1; i < arr.length; i++)
        {
            if(arr[i][column] != 0)
            {
                double constant = arr[i][column] / arr[row][column];
                // j = 0 may be redundant. j = column is sufficient?
                for(int j = 0; j < arr[0].length; j++)
                    arr[i][j] = arr[i][j] - constant * arr[row][j];
            }
        }
    }
    public static void swap(double[][] arr, int row1, int row2)
    {
        double[] temp = arr[row1];
        arr[row1] = arr[row2];
        arr[row2] = temp;
    }
    public static double[][] arrayReader(String input) throws Exception
    {
        int rows = 0;
        input = input.replace(" ","");
        input = input.replace("[","");
        String[] rowlist = input.split("]");
        String[][] matrix = new String[rowlist.length][];
        for(int i = 0; i < rowlist.length; i++)
            matrix[i] = rowlist[i].split(",");
        double[][] returning = new double[matrix.length][matrix[0].length];
        for(int i = 0; i < returning.length; i++)
            for(int j = 0; j < returning[0].length; j++)
                returning[i][j] = Double.valueOf(matrix[i][j]);
        return returning;
    }
    public static void printarr(double[][] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
            {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
}
