import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GetSizePanel extends JPanel {
	private int rows, cols;
	private JTextField rowsTextField = new JTextField();
	private JTextField colsTextField = new JTextField(); 
	private JButton submitButton = new JButton("Submit");

	public GetSizePanel(Controller controller) {
		super();
		
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((Integer.parseInt(rowsTextField.getText()) >= 3) &&
						Integer.parseInt(colsTextField.getText()) >= 3) {
					rows = Integer.parseInt(rowsTextField.getText());
					cols = Integer.parseInt(colsTextField.getText());
					GetSizePanel.this.getParent().setVisible(false);
					controller.finished();
				} else {
					JOptionPane.showMessageDialog(GetSizePanel.this, "Matrix size mjust be greater than 2", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel("Welcom to Life Game!"));
		add(new JLabel("Please enter number of rows and columns(at least 3)."));
		
		JPanel grid = new JPanel(new GridLayout(2, 2));
		grid.add(new JLabel("Rows: "));
		grid.add(rowsTextField);
		grid.add(new JLabel("Columns: "));
		grid.add(colsTextField);
		add(grid);
		
		add(submitButton);
	}

	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
}
