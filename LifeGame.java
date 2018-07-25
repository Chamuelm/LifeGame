import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/* 
 * File: LifeGame.java
 * This class maintain GUI for Life Game instance  
 * 
 * Created by Moshe Hamiel - ID 308238716
 */
public class LifeGame {
		
	
	public static void main(String[] args) {
		int rows, cols;
		
		// Window to get size from user 
		Controller inputReceived = new Controller(1);
		GetSizePanel sizePanel = new GetSizePanel(inputReceived);
		JFrame sizeWindow = new JFrame("Life Game: Enter matrix size");
		sizeWindow.setSize(400, 150);
		sizeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sizeWindow.add(sizePanel);
		sizeWindow.setVisible(true);
		
		inputReceived.waitForThreads(); // Wait for input from user before create main window
		rows = sizePanel.getRows();
		cols = sizePanel.getCols();
		sizeWindow.dispose();
		
		
		// GUI window creation
		LifePanel panel = new LifePanel(rows, cols);
		JFrame mainWindow = new JFrame("Life Game");
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setSize(450, 500);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.add(panel, BorderLayout.CENTER);
		
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(panel);
		mainWindow.add(restartButton, BorderLayout.SOUTH);
		mainWindow.setVisible(true);
	}
}
	

