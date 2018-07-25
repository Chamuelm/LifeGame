/* 
 * File: CellNextModeUpdater.java
 * A thread to manage cell generation - check next state and update when generation is changed  
 * 
 * Created by Moshe Hamiel - ID 308238716
 */
public class CellNextModeUpdater implements Runnable {
	int row, col;						// Cell location in matrix to update
	LifeMatrix matrix;					// Matrix instance to update its cell
	Controller finishCalculation;		// Controller to control updating time

	public CellNextModeUpdater(int row, int col, LifeMatrix matrix, Controller finishCalculation) {
		this.row = row;
		this.col = col;
		this.matrix = matrix;
		this.finishCalculation = finishCalculation;
	}

	@Override
	public void run() {
		// Calculate next cell state
		int livingNeighbors = checkLivingNeighbors();
		int currCellMode = matrix.getCell(row, col).getMode();
		int nextCellMode;
		
		if (currCellMode == LifeMatrix.LIFE) {
			if (livingNeighbors == 2 || livingNeighbors == 3)
				nextCellMode = LifeMatrix.LIFE;
			else
				nextCellMode = LifeMatrix.DEATH;
		} else {
			if (livingNeighbors == 3)
				nextCellMode = LifeMatrix.LIFE;
			else
				nextCellMode = LifeMatrix.DEATH;
		}
		
		// Update next state in cell object
		matrix.getCell(row, col).setNextMode(nextCellMode);
		
		// Announce finished and wait for others cell to finish
		finishCalculation.finishedAndWait();
		
		// Proceed generation
		matrix.getCell(row, col).proceedGeneration();
	}
	
	// Check living neighbors of the cell
	private int checkLivingNeighbors() {
	int count = 0;
		
		// Left-up corner
		if (row > 0 && col > 0) 
			count += matrix.getCell(row-1, col-1).getMode();

		// Upper cell
		if (row > 0)
			count += matrix.getCell(row - 1, col).getMode();

		// Right-up corner
		if (row > 0 && col < matrix.getCols()-1) 
			count += matrix.getCell(row-1, col+1).getMode();

		// Left cell
		if (col > 0) 
			count += matrix.getCell(row, col-1).getMode();
		
		// Right cell
		if (col < matrix.getCols()-1)
			count += matrix.getCell(row, col+1).getMode();
		
		// Left-down corner
		if (row < matrix.getRows()-1 && col > 0)
			count += matrix.getCell(row+1, col-1).getMode();

		// Down cell
		if (row < matrix.getRows()-1)
			count += matrix.getCell(row+1, col).getMode();
		
		// Right-down cell
		if (row < matrix.getRows()-1 && col < matrix.getCols()-1)
			count += matrix.getCell(row+1, col+1).getMode();
		
		return count;
	}
}
