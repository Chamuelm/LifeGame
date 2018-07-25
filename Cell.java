/* 
 * File: Cell.java
 * This class contains Cell information  
 * 
 * Created by Moshe Hamiel - ID 308238716
 */
public class Cell {
	private int mode;		// Current state
	private int nextMode;	// Next generation state		

	// Constructor
	public Cell(int mode) {
		this.mode = mode;
	}
	
	public void proceedGeneration() {
		mode = nextMode;
	}
	
	// Getter for mode
	public int getMode() {
		return mode;
	}
	
	// Setter for next mode
	public void setNextMode(int nextMode) {
		this.nextMode = nextMode;
	}
}
