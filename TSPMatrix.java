import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TSPMatrix {
	private int adjMatrix[][];
	private int nNode;



	public int[][] getAdjMatrix(){
		return adjMatrix;
	}

	public int getnNode(){
		return nNode;
	}

	public void inputMatrixFromKey(){
		Scanner reader = new Scanner(System.in);
		System.out.print("Masukkan jumlah node : ");
		nNode = reader.nextInt();
		adjMatrix = new int[nNode][nNode];

		for (int i=0 ;i<nNode;i++) {
			for (int j=0; j<nNode;j++){
				adjMatrix[i][j] = reader.nextInt();
			}
		}
	}


	public void inputMatrixFromFile(){
		Scanner reader = new Scanner(System.in);
		InputStream stream;
		BufferedReader buffer;
		String filename;
		String line;
		int row = 0;

		System.out.print("Masukkan nama file : ");
		filename = reader.nextLine();
		
		try {
			stream = ClassLoader.getSystemResourceAsStream(filename);
			buffer = new BufferedReader(new FileReader(filename));

			line = buffer.readLine();
			String[] vals = line.trim().split("\\s+");
			nNode = vals.length;
			adjMatrix = new int[nNode][nNode];

			do {
				vals = line.trim().split("\\s+");
				for (int i = 0 ; i < nNode ; i++){
					adjMatrix[row][i] = Integer.parseInt(vals[i]);
				}
				row++;
			}
			while ((line = buffer.readLine()) != null);

		
		}
		catch(IOException e) {
			System.out.println("File " + filename + " tidak dapat dibuka..");
		}
	}


	public void showMatrix(){
		for (int i=0 ;i<nNode;i++) {
			for (int j=0; j<nNode;j++){
				System.out.print(adjMatrix[i][j]+" ");
			}
			Printer.print(" ");
		}
	}



	public static void main(String args[]){
		TSPMatrix TSP = new TSPMatrix();
		TSP.inputMatrixFromFile();
		TSP.showMatrix();
	}


}