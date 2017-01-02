package softgroup;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class task3 extends JFrame {

	public static void find(String str) {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < str.length(); i++) {
			if (str.substring(i, i + 1).matches("[-+]?\\d*\\.?\\d+")) {
				nums.add(new Integer(Integer.parseInt(num(i, str))));
				i = i + num(i, str).length() - 1;
				if (i + 2 < str.length() && str.substring(i + 2, i + 3).matches("[-+]?\\d*\\.?\\d+")) {
					if (str.substring(i + 1, i + 2).contains("*")) {
						nums.add(new Integer(nums.get(nums.size() - 1) * Integer.parseInt(num(i + 2, str))));
					} else if (str.substring(i + 1, i + 2).contains("/")) {
						nums.add(new Integer(nums.get(nums.size() - 1) / Integer.parseInt(num(i + 2, str))));
					} else if (str.substring(i + 1, i + 2).contains("+")) {
						nums.add(new Integer(nums.get(nums.size() - 1) + Integer.parseInt(num(i + 2, str))));
					} else if (str.substring(i + 1, i + 2).contains("-")) {
						nums.add(new Integer(nums.get(nums.size() - 1) - Integer.parseInt(num(i + 2, str))));
					}
					nums.remove(nums.size() - 2);
					i = i + Integer.toString(nums.get(nums.size() - 1)).length() + 1;
				}
			}
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < nums.size(); i++) {
			System.out.println(nums.get(i));
			if (max < nums.get(i))
				max = nums.get(i);
			if (min > nums.get(i))
				min = nums.get(i);
		}
		System.out.println("Max: " + max);
		System.out.println("Min: " + min);
	}

	public static String num(int n, String s) {
		if (s.length() < n + 1 || !s.substring(n, n + 1).matches("[-+]?\\d*\\.?\\d+")) {
			return "";
		}
		return s.substring(n, n + 1) + num(n + 1, s);
	}

	public static void main(String[] args) {
		// find("2safdf2-5s--*=+/ads16/4fggjgj3*7sadsaf5+7g8fd__hj_)gjdxz34");
		new task3();
	}

	// =========================================================================

	public task3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 120);
		setLocation(500, 500);
		setVisible(true);
		setResizable(true);
		setTitle("MatLab5");

		JPanel jp = new JPanel();
		add(jp);

		JLabel jl = new JLabel();
		jp.add(jl);
		jl.setText("Input");

		JTextField input = new JTextField(35);
		jp.add(input);
		input.grabFocus();

		JButton OK = new JButton();
		jp.add(OK);
		OK.setText("OK");

		setLayout(new BorderLayout());

		OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				find(input.getText());
			}
		});

	}

}
