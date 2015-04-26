import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WriteOpb {
	public void writeOpb(int[][] arr,int piecesOfWork,int shifts,String filename)
	{
		try {
			BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(filename)));
			//Write goal function
			outputWriter.append("min:");
			for(int i=1;i<=shifts;i++)
			{
				outputWriter.append(" +1*x"+i);
			}
			outputWriter.append(";");
			//Only one shift is chosen among all the shifts that cover a common piece of work
			outputWriter.newLine();
			for(int i=0;i<piecesOfWork;i++)
			{
				for(int j=0;j<shifts;j++)
				{
					if(arr[i][j] == 1)
					{
						outputWriter.append("+1*x"+(j+1)+" ");
					}
				}
				outputWriter.append("=1;");
				outputWriter.newLine();
			}
			//All the works should be covered
			for(int i=1;i<=shifts;i++)
			{
				outputWriter.append("-1*x"+(i)+" ");
			}
			outputWriter.append(">= "+(-piecesOfWork)+";");
			outputWriter.newLine();
			outputWriter.flush();
			outputWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
