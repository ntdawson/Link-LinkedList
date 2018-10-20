package datastructures.linkedlist.linkedlist;

import java.util.Collection;

import datastructures.linkedlist.link.Node;

public class LinkedList<E> {

	transient int size;
	transient Node<E> first;
	transient Node<E> last;

	public LinkedList() {
	}

	public LinkedList(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	/*
	 * Insert collection into this list
	 */
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size, c);
	}

	private void checkPositionByIndex(int index) {
		if (!positionExists(index)) {
			throw new IndexOutOfBoundsException("That index refers to nothing in this list");
		}
	}

	private boolean positionExists(int index) {
		return index >= 0 && index <= size;
	}

	/*
	 * Insert collection into list at specific index
	 */
	private boolean addAll(int index, Collection<? extends E> c) {
		checkPositionByIndex(index);

		Object[] a = c.toArray();
		int cLength = a.length;
		if (cLength == 0) {
			return false;
		}

		Node<E> before, after;

		if (index == size) {
			after = null;
			before = last;
		} else {
			after = node(index);
			before = after.getPrev();
		}

		for (Object o : a) {
			@SuppressWarnings("unchecked")
			E e = (E) o;
			Node<E> newNode = new Node<E>(e, before, after);
			if (before == null)
				first = newNode;
			else
				before.setNext(newNode);
		}

		size += cLength;
		return true;

	}

	public E get(int index) {
		checkPositionByIndex(index);
		return node(index).getData();
	}

	Node<E> node(int index) {
		/*
		 * "size >> 1" is a cute way to handle 0 and 1 size lists bitwise shifting in
		 * this instance is basically dividing size by 2
		 */
		if (index < (size >> 1)) {
			Node<E> x = first;
			for (int i = 0; i < size; i++)
				x = x.getNext();
			return x;
		} else {
			Node<E> x = last;
			for (int i = size - 1; i > index; i--)
				x = x.getPrev();
			return x;
		}
	}

}
