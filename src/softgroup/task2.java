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
public class task2 extends JFrame {

	public static long fact(long n) {
		if (n == 0)
			return 1;
		return n * fact(n - 1);
	}

	public static long m_num(int n, int m) {
		return fact(n) / (fact(m) * fact(n - m));
	}

	public static void equat(int a, int b, int n) {
		String[] str = new String[n + 1];
		for (int i = 0; i < str.length; i++) {
			str[i] = "";
		}
		// a
		for (int i = 0; i < n - 1; i++) {
			str[i] = str[i] + "a^" + (n - i);
		}
		str[n - 1] = "a" + str[n - 1];
		// *
		for (int i = 1; i < str.length - 1; i++) {
			str[i] = str[i] + "*";
		}
		str[1] = str[1] + "b";
		// b
		for (int i = 2; i < n + 1; i++) {
			str[i] = str[i] + "b^" + (i);
		}

		for (int i = 1; i < str.length - 1; i++) {
			str[i] = m_num(n, i) + "*" + str[i];
		}

		for (int i = 0; i < str.length - 1; i++) {
			System.out.print(str[i] + " + ");
			result = result + str[i] + " + ";
		}
		System.out.print(str[str.length - 1] + " = " + (int) Math.pow((a + b), n));
		result = result + str[str.length - 1] + " = " + (int) Math.pow((a + b), n);
	}

	public static void main(String[] args) {
		// equat(3, 5, 4);
		new task2();
	}

	// ==================================================================


	static String result = "";
	
	public task2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 120);
		setLocation(300, 100);
		setVisible(true);
		setResizable(true);
		setTitle("MatLab5");

		JPanel jp = new JPanel();
		add(jp);

		JLabel jl = new JLabel();
		jp.add(jl);
		jl.setText("Write: a b n");

		JTextField input = new JTextField(20);
		jp.add(input);
		input.grabFocus();

		JButton OK = new JButton();
		jp.add(OK);
		OK.setText("OK");

		setLayout(new BorderLayout());

		OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int field = 0, k = 0;
				String s = input.getText(), a = "", b = "", n = "";
				for (int i = 0; i < s.length(); i++) {
					if (s.substring(i, i + 1).equals(" ") | (field == 2 && i == s.length() - 1)) {
						if (field == 0) {
							a = s.substring(k, i);
						} else if (field == 1) {
							b = s.substring(k, i);
						} else if (field == 2) {
							n = s.substring(k, i + 1);
							break;
						}
						field++;
						k = i + 1;
					}
				}
				equat(Integer.parseInt(a), Integer.parseInt(b), Integer.parseInt(n));
				JOptionPane.showMessageDialog(null, result);
				input.grabFocus();
			}
		});

	}

}
