import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

/* 
 * File: LifeMatrix.java
 * This class contains life game matrix and manages a game instance 
 * 
 * Created by Moshe Hamiel - ID 308238716
 */
public class LifeMatrix implements Runnable {
	// Constants
	private static Random rand = new Random();
	private static long generationDelay = 1500; // milliseconds
	public static int LIFE = 1;
	public static int DEATH = 0;
	
	// Instance variables
	private int rows, cols;		// Matrix size
	private Cell[][] matrix;
	private JPanel parent;		// Parent component (used to repaint after generation switch)

	// Constructor
	public LifeMatrix(int rows, int cols, JPanel parent) {
		this.rows = rows;
		this.cols = cols;
		matrix = new Cell[rows][cols];
		this.parent = parent;
		
		// Matrix initialization
		for(int i=0; i<rows; i++) 
			for (int j=0; j<cols; j++)
				matrix[i][j] = new Cell(rand.nextInt(1024)%2);
	}
	
	@Override
	public void run() {
		while(true)
			generationProcess();
		
	}
	
	// Manages generations switching 
	public void generationProcess() {
		// Controller for cells who finished calculation in current generation 
		Controller finishCalculation = new Controller(rows*cols);				
		
		ExecutorService executor = Executors.newFixedThreadPool(rows*cols);
		try {
			Thread.sleep(generationDelay);	// Delay to see changes between generations

			// Create processing thread for each cell
			for(int i=0; i<rows; i++) 
				for (int j=0; j<cols; j++)
					executor.execute(new CellNextModeUpdater(i, j, this, finishCalculation));
			
			// Wait for threads to finish calculate
			finishCalculation.waitForThreads();			
			executor.awaitTermination(generationDelay, TimeUnit.MILLISECONDS);
			executor.shutdown();
			parent.repaint();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Getter for specified cell
	public Cell getCell(int row, int col) {
		return matrix[row][col];
	}
	
	// Getter for matrix columns number
	public int getCols() {
		return cols;
	}
	
	// Getter for matrix rows number
	public int getRows() {
		return rows;
	}


}
