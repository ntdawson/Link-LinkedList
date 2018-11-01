package datastructures.linkedlist.testing;

import java.util.Arrays;

import datastructures.linkedlist.linkedlist.LinkedList;

public class Testing {

	public static void main(String[] args) {

		String[] s = new String[] { "One", "two", "three", "four", "five" };
		LinkedList<String> k = new LinkedList<String>(Arrays.asList(s));

		// system.out.println(k.get(5));
		// System.out.println(k.removeFirst());
		// System.out.println(k.removeLast());
		k.addFirst("first");
		k.addLast("last");
		System.out.println(k.toString());
	}

}
