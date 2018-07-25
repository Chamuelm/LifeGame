import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/* 
 * File: LifePanel.java
 * This class contains the Life game instance and manage its look on the screen  
 * 
 * Created by Moshe Hamiel - ID 308238716
 */
@SuppressWarnings("serial")
public class LifePanel extends JPanel implements ActionListener {
	private final int rows;			// Number of rows in matrix
	private final int cols;			// Number of columns in matrix		
	
	private LifeMatrix matrix;		// Instance of matrix
	private Thread aThread;			// Thread to start generations proceeding
	
	// Constructor creates new game instance
	public LifePanel(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		matrix = new LifeMatrix(rows, cols, this);
		
		aThread = new Thread(matrix);
		aThread.start();
	}
	
	// Draw the matrix on the screen
	public void paintComponent(Graphics g) {
		int cellSize;
		super.paintComponent(g);
		if (this.getHeight()/rows < this.getWidth()/cols)
			cellSize = this.getHeight()/rows;
		else
			cellSize = this.getWidth()/cols;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				g.drawRect(j*cellSize, i*cellSize, cellSize, cellSize);
				if (matrix.getCell(i, j).getMode() == LifeMatrix.LIFE)
					g.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
			}
		}
	}
	
	// Restart the matrix
	public void restart() {
		matrix = new LifeMatrix(rows, cols, this);
		aThread = new Thread(matrix);
		aThread.start();
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		restart();
    }
} // End of class LifePanel