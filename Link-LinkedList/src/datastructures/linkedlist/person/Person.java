package datastructures.linkedlist.person;


import java.util.Comparator;

public class Person {
	String firstName;
	String lastName;
	String address;
	String city;
	String state;
	String zip;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Person(String firstName, String lastName, String address, String city, String state, String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	/*
	 * This is the easy way to compare things...
	 */
	public static Comparator<Person> COMPARE_BY_FIRSTNAME = new Comparator<Person>() {
		public int compare(Person one, Person other) {
			return one.firstName.compareTo(other.firstName);
		}
	};
	public static Comparator<Person> COMPARE_BY_LASTNAME = new Comparator<Person>() {
		public int compare(Person one, Person other) {
			return one.lastName.compareTo(other.lastName);
		}
	};

	public static Comparator<Person> COMPARE_BY_ADDRESS = new Comparator<Person>() {
		public int compare(Person one, Person other) {
			return one.address.compareTo(other.address);
		}
	};
	public static Comparator<Person> COMPARE_BY_CITY = new Comparator<Person>() {
		public int compare(Person one, Person other) {
			return one.city.compareTo(other.city);
		}
	};
	public static Comparator<Person> COMPARE_BY_STATE = new Comparator<Person>() {
		public int compare(Person one, Person other) {
			return one.state.compareTo(other.state);
		}
	};
	public static Comparator<Person> COMPARE_BY_ZIP = new Comparator<Person>() {
		public int compare(Person one, Person other) {
			return one.zip.compareTo(other.zip);
		}
	};

	public String print() {
		return firstName + " " + lastName + " " + address + " " + city + " " + state + " " + zip;
	}

}
