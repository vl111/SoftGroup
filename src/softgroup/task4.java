package softgroup;

import static softgroup.Kind.*;

public class task4 {

	public static void main(String[] args) {
		Rental r1 = new Rental(k1, 2, 5.7);
		Customer c1 = new Customer(1, "James", r1);
		System.out.println(c1.getRental().getAmountFor());
	}

}

class Customer {

	private String name;
	private Rental rental;
	private int id;

	// rent and id was added and rent getter, setter
	public Customer(int id, String name, Rental rental) {
		this.id = id;
		this.name = name;
		this.rental = rental;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

}

class Rental {
	private Kind kind;
	private int days;
	private double price;
	private double amountFor;

	// price and amountFor was added
	public Rental(Kind kind, int days, double price) {
		this.kind = kind;
		this.days = days;
		this.price = price;
		this.amountFor = amountFor();
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
		this.amountFor = amountFor();
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
		this.amountFor = amountFor();
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
		this.amountFor = amountFor();
	}

	private double getBasePrice() {
		return days * price;
	}

	public double getAmountFor() {
		return amountFor;
	}

	/*
	 * Method amountFor(...) was moved into class Rental because it works with
	 * fields days and kind, also this method calculates rent, so it should be in class Rental.
	 */
	private double amountFor() {
		double result = days * getBasePrice();

		switch (kind) {
		case k1:
			result *= 1.5;

		case k2:
			result *= 2;

		case k3:
			result *= 2.5;
		}

		if (days > 7) {
			result *= 3;
		}
		return result;
	}
}
