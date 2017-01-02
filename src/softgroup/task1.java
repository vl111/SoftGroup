package softgroup;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class task1 extends JFrame {

	public static long fact(long n) {
		if (n == 0)
			return 1;
		return n * fact(n - 1);
	}

	public static long m_num(int n, int m) {
		return fact(n) / (fact(m) * fact(n - m));
	}

	public static String pascal(int n, int m) {
		if (n == m)
			return "1";
		return Long.toString(m_num(n, m)) + " " + pascal(n, m + 1);
	}

	public static void main(String[] args) {
		// System.out.println(pascal(4, 0));
		new task1();

	}

	// ========================================================================

	public task1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 120);
		setLocation(300, 80);
		setVisible(true);
		setResizable(false);
		setTitle("MatLab5");

		JPanel jp = new JPanel();
		add(jp);

		JLabel jl = new JLabel();
		jp.add(jl);
		jl.setText("n row:");

		JTextField input = new JTextField(18);
		jp.add(input);
		input.grabFocus();

		JButton OK = new JButton();
		jp.add(OK);
		OK.setText("OK");

		setLayout(new BorderLayout());

		OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (input.getText().length() > 0 && Integer.parseInt(input.getText()) <= 20) {
					JOptionPane.showMessageDialog(null, " " + pascal(Integer.parseInt(input.getText()), 0));
					input.setText("");
					input.grabFocus();
				}
			}
		});

	}
}
