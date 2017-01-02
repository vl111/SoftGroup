package softgroup;

import static softgroup.SalaryType.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

public class task5 {

	static File fileInput;
	static FileReader fr;
	static BufferedReader br;

	static File fileOut;
	static FileWriter fw;
	static BufferedWriter bw;

	static String path1 = ".\\inputTask5.txt";
	static String path2 = ".\\outputTask5.txt";

	static List<Employee> es;

	public static void reader(String path) throws IOException {
		fileInput = new File(path);
		if (!fileInput.exists()) {
			fileInput.createNewFile();
		}
		fr = new FileReader(fileInput);
		br = new BufferedReader(fr);

		es = new ArrayList<Employee>();

		boolean read = true;
		while (read) {
			String s = br.readLine();
			if (s.contains("#")) {
				read = false;
				break;
			}

			String[] fields = new String[4];
			fields = fields(s, 0, fields, 0);

			// e) Code for handling the incorrect format of incoming file
			try {
				es.add(new Employee(Integer.parseInt(fields[0]), fields[1], Double.parseDouble(fields[2]),
						SalaryType.valueOf(fields[3])));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Wrong number format, row: " + (es.size() + 1));
			} catch (IllegalArgumentException e1) {
				JOptionPane.showMessageDialog(null, "Wrong salary type format, row: " + (es.size() + 1));
			}
		}

	}

	public static void writer(String path) throws IOException {
		fileOut = new File(path);
		if (!fileOut.exists()) {
			fileOut.createNewFile();
		}
		fw = new FileWriter(fileOut);
		bw = new BufferedWriter(fw);

		for (int i = 0; i < es.size(); i++) {
			bw.write(es.get(i).toString());
			bw.newLine();
		}
		bw.write("#####");

		bw.close();
		fw.close();
	}

	public static String[] fields(String s, int n, String[] arr, int field) {
		for (int i = n; i < s.length(); i++) {
			if (s.substring(i, i + 1).equals("/") && field < 4) {
				arr[field] = s.substring(n, i);
				fields(s, i + 1, arr, field + 1);
				break;
			}
		}
		return arr;
	}

	public static void main(String[] args) throws IOException {

		reader(path1);

		es = Logic.sort(es);

		writer(path2);

		// b) Write information about first five employees from collection
		for (int i = 0; i < 5; i++)
			System.out.println(es.get(i).toString());

		// c) Write ID of three last employees from collection
		for (int i = es.size() - 3; i < es.size(); i++)
			System.out.println(es.get(i).getId());
	}

}

class Employee {
	private String name;
	private int id;
	private double avMonSal;
	private SalaryType salaryType;
	private double salary;

	public Employee(int id, String name, double salary, SalaryType salaryType) {
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.salaryType = salaryType;

		avMonSal = Logic.calc(this.salary, this.salaryType);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
		avMonSal = Logic.calc(this.salary, this.salaryType);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAvMonSal() {
		return avMonSal;
	}

	public SalaryType getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(SalaryType salaryType) {
		this.salaryType = salaryType;
		avMonSal = Logic.calc(this.salary, this.salaryType);
	}

	@Override
	public String toString() {
		return id + "/" + name + "/" + salary + "/" + salaryType + "/" + avMonSal;
	}
}

class Logic {

	// Calculation of the average month salary
	public static double calc(double salary, SalaryType salaryType) {
		if (salaryType.equals(hourly)) {
			salary = 20.8 * 8 * salary;
		}
		return salary;
	}

	/*
	 * a) Sort the collection of employees in descending order by the average
	 * monthly salary. In the case of equal salary – by the name
	 */
	public static List<Employee> sort(List<Employee> employees) {
		Collections.sort(employees, new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				if (e1.getAvMonSal() - (int) e2.getAvMonSal() == 0)
					return e1.getName().compareTo(e2.getName());
				return (int) e2.getAvMonSal() - (int) e1.getAvMonSal();
			}
		});

		return employees;
	}

}