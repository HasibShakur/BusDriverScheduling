import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *  This class has a main function with the following inputs:
*	1. Output filename and with full path which contains the satisfied clauses
* 	2. Filename along with full path for writing down the matrix
*	Finally it outputs the possible arrangement of the shifts which will satisfy the given constraints
 */
 
 /**
 * @author MD HASIB BIN SHAKUR
 * 22C:196 High Performance SAT and SMT solver
 * ID: 00846429
 */

public class ParseOutput {

	public static void main(String[] args) 
	{
		try {
			BufferedReader outputReader = new BufferedReader(new FileReader(new File(args[0])));
			String str = null;
			str = outputReader.readLine();
			String[] temp = str.replaceAll(" ", ",").split(",");
			// Add only the positive(satisfied) shifts from the input file  into an arraylist
			ArrayList<Integer> optimumShifts = new ArrayList<Integer>();
			String s = null;
			for(int i=0;i<temp.length;i++)
			{
				if(!temp[i].startsWith("-"))
				{
					s = temp[i].substring(1);
					optimumShifts.add(Integer.parseInt(s));
					s = null;
				}
			}
			// Now map all the satisfied shifts with the matrix of [piecesOfWork][shifts] for finding the satisfied shifts and all the tasks they have covered
			BufferedReader matrixReader = new BufferedReader(new FileReader(new File(args[1])));
			String matrixRow = null;
			ArrayList<String> matrixLine = new ArrayList<String>();
			// Replace all the inner spaces inside the matrix with ""
			while((matrixRow = matrixReader.readLine())!=null )
			{
				matrixRow = matrixRow.replaceAll(" ", "");
				matrixLine.add(matrixRow);
			}
			int numberOfRows = matrixLine.size();
			int numberOfColumns = matrixLine.get(0).length();
			// Create an array of row number equal to the number of rows of the matrix and column number equal to the number of columns of the matrix
			int[][] arr = new int[numberOfRows][numberOfColumns];
			for(int i=0;i<matrixLine.size();i++)
			{
				for(int j=0;j<matrixLine.get(i).length();j++)
				{
					// As each value will be a character (either 0 or 1) deduct 48(ASCII) value from each of them
					arr[i][j] = matrixLine.get(i).charAt(j)-48;
				}
			}
			int count = 0;
			System.out.println("Task Covered"+"	"+"Tasks");
			System.out.println("----------------------------");
			for(int i = 0;i<optimumShifts.size();i++)
			{
				for(int k = 0;k<numberOfRows;k++)
				{
					if(arr[k][optimumShifts.get(i)-1]==1)
					{
						count+=1;
					}
				}
				System.out.print(count + "		");
				for(int j=0;j<numberOfRows;j++)
				{
					if(arr[j][optimumShifts.get(i)-1] == 1)
					{
						System.out.print(j+" ");
					}
				}
				count = 0;
				System.out.println();
			}
			matrixReader.close();
			outputReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
