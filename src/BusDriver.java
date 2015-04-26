import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Hasib
 *
 */
public class BusDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if(args == null || args.length == 0)
			{
				throw new FilNotFoundException(".opb file name not given");
			}
			String filename = args[0];
			BufferedReader  inputReader = new BufferedReader(new FileReader(new File(filename)));
			String firstLine = inputReader.readLine().trim();
			firstLine = firstLine.replaceAll(" ", ",");
			String[] temp = firstLine.split(",");
			int piecesOfWork = Integer.parseInt(temp[0]);
			int shifts = Integer.parseInt(temp[1]);
			//int minimumColumnsForPartition = Integer.parseInt(temp[2]);
			int[][] arr = new int[piecesOfWork][shifts];
			for(int i=0;i<piecesOfWork;i++)
			{
				for(int j=0;j<shifts;j++)
				{
					arr[i][j]=0;
				}
			}
			String str = null;
			int col = 0;
			while((str = inputReader.readLine())!=null)
			{
				temp = null;
				temp = str.trim().replaceAll(" ", ",").split(",");
				int[] tempInt = new int[temp.length-2];
				for(int i=2;i<temp.length;i++)
				{
					tempInt[i-2] = Integer.parseInt(temp[i]);
				}
				for(int i=0;i<tempInt.length;i++)
				{
					arr[tempInt[i]][col]=1;
				}
				col += 1;
			}
			for(int i=0;i<piecesOfWork;i++)
			{
				for(int j=0;j<shifts;j++)
				{
					System.out.print(arr[i][j]+ " ");
				}
				System.out.println();
			}
			WriteOpb w = new WriteOpb();
			w.writeOpb(arr, piecesOfWork, shifts, "c1agen.opb");
			inputReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
