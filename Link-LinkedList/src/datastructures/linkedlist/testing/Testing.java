package datastructures.linkedlist.testing;

import java.util.Arrays;

import datastructures.linkedlist.linkedlist.LinkedList;

public class Testing {

	public static void main(String[] args) {

		// create a collection of strings
		String[] s = new String[] { "One", "two", "three", "four", "five" };
		String[] toInsert = new String[] { "Six", "Seven", "Eight" };

		// construct a new LinkedList with that collection
		LinkedList<String> k = new LinkedList<String>(Arrays.asList(s));

		System.out.println(k.printAll());

		System.out.println("The 5th element is: " + k.get(5));

		System.out.println("The first element was: " + k.removeFirst());

		System.out.println("The last element was: " + k.removeLast());

		k.addFirst("first");
		k.addLast("last");
		k.add(4, "4th element");

		System.out.println("The 4th element is: " + (k.get(4)));

		System.out.println(k.printAll());

		k.addAll(3, Arrays.asList(toInsert));

		System.out.println(k.printAll());

	}

}
